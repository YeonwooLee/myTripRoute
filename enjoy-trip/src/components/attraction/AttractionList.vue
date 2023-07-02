<template>
  <v-container>
    <view-header type="attraction"></view-header>
    <v-row align="center">
      <v-col class="d-flex" cols="12">
        <v-select :items="sidos" label="시도" outlined v-model="sido"></v-select>
        <v-select :items="guguns" label="구군" outlined v-model="gugun"></v-select>
        <v-select
          :items="contentTypes"
          label="관광지 유형"
          outlined
          v-model="contentType"
        ></v-select>
        <v-text-field
          label="*키워드"
          placeholder="*키워드"
          outlined
          v-model="keyword"
          ref="keywordTextArea"
          @keypress.enter="getAttraction"
        ></v-text-field>
        <v-btn depressed color="primary" x-large v-on:click="getAttraction"> 검색 </v-btn>
      </v-col>
    </v-row>

    <kakao-map type="카카오맵 자리" :attractions="attractions"></kakao-map>

    <v-data-table
      :headers="attractionHeaders"
      :items="attractions"
      :single-expand="singleExpand"
      :expanded.sync="expanded"
      item-key="contentid"
      show-expand
      class="elevation-1"
    >
      <template v-slot:[`item.firstimage`]="{ item }"
        ><v-img :src="item.firstimage" width="100" />
      </template>

      <template v-slot:top>
        <v-toolbar flat>
          <v-toolbar-title>관광지 검색 결과</v-toolbar-title>

          <v-spacer></v-spacer>

          <!-- 계획 추가 -->
          <v-dialog v-model="dialog" max-width="500px">
            <v-card>
              <v-card-title>
                <span class="text-h5">여행 계획 선택</span>
              </v-card-title>

              <v-card-text>
                <v-container>
                  <v-select
                    :items="trips"
                    item-text="tripName"
                    item-value="tripId"
                    label="여행 계획 목록"
                    return-object
                    prepend-icon="mdi-map"
                    solo
                    v-model="select"
                  ></v-select>
                </v-container>
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn color="blue darken-1" text @click="save"> 추가 </v-btn>
                <v-btn color="blue darken-1" text @click="close"> 닫기 </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>

          <v-spacer></v-spacer>

          <v-switch v-model="singleExpand" label="Single expand" class="mt-2"></v-switch>
        </v-toolbar>
      </template>

      <template v-slot:[`item.actions`]="{ item }">
        <div class="d-flex justify-center align-center">
          <v-icon class="mr-2" @click="open(item)"> mdi-calendar-edit </v-icon>
        </div>
      </template>

      <!-- 상세 정보 -->
      <template v-slot:expanded-item="{ headers, item }">
        <td :colspan="headers.length">
          <div class="my-3">{{ item["overview"] }}</div>
        </td>
      </template>
    </v-data-table>
  </v-container>
</template>

<script>
import { listSido, listGugun, listAttraction, detailCommon } from "@/api/attraction";
import { getAttractionDetail } from "@/api/tripDetail";
import ViewHeader from "@/components/ViewHeader.vue";
import KakaoMap from "@/components/attraction/kakaomap/KakaoMap.vue";
import { mapActions, mapState } from "vuex";

const basicParam = {
  serviceKey: process.env.VUE_APP_ATTRACTION_API_KEY,
  numOfRows: "20",
  pageNo: "1",
  MobileOS: "ETC",
  MobileApp: "AppTest",
  _type: "json",
};

export default {
  name: "AttractionList",
  components: {
    ViewHeader,
    KakaoMap,
  },

  data() {
    return {
      insertingPlanLen: 0,
      sidos: [],
      sido: "",
      guguns: [],
      gugun: "",

      contentTypes: [
        { value: null, text: "선택x" },
        { value: 12, text: "관광지" },
        { value: 14, text: "문화시설" },
        { value: 15, text: "축제공연행사" },
        { value: 25, text: "여행코스" },
        { value: 28, text: "레포츠" },
        { value: 32, text: "숙박" },
        { value: 38, text: "쇼핑" },
        { value: 39, text: "음식점" },
      ],
      contentType: "",

      attractions: [],
      keyword: "",

      expanded: [],
      singleExpand: false,

      details: {},

      dialog: false,
      editedIndex: -1,
      select: null,

      attractionHeaders: [
        {
          text: "대표이미지",
          align: "start",
          sortable: false,
          value: "firstimage",
        },
        { text: "관광지명", value: "title" },
        { text: "주소", value: "addr1" },
        { text: "위도", value: "mapy" },
        { text: "경도", value: "mapx" },
        { text: "여행 계획에 추가하기", value: "actions", sortable: false },
      ],
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
    ...mapState("tripStore", ["trips"]),
  },
  methods: {
    ...mapActions("tripStore", ["getTrips"]),
    ...mapActions("planStore", ["addPlan"]),
    getDetail(contentId, item) {
      const param = JSON.parse(JSON.stringify(basicParam));

      param["defaultYN"] = "Y";
      param["overviewYN"] = "Y";
      param["contentId"] = contentId;

      detailCommon(
        param,
        item,
        (res) => {
          const temp = res.data.response.body.items.item[0];
          const finalResult = JSON.parse(JSON.stringify(temp));

          item["overview"] = finalResult["overview"];
          // item["detail"] = finalResult;
        },
        (err) => {
          console.log(err);
        }
      );
    },
    getSido(param) {
      listSido(
        param,
        ({ data }) => {
          this.sidos = data.response.body.items.item.map((item) => {
            return {
              text: item.name,
              value: item.code,
            };
          });
          this.sidos.splice(0, 0, { text: "선택x", value: null });
        },
        (err) => {
          console.error(err);
        }
      );
    },
    getGugun(param) {
      listGugun(
        param,
        ({ data }) => {
          this.guguns = data.response.body.items.item.map((item) => {
            return {
              text: item.name,
              value: item.code,
            };
          });
          this.guguns.splice(0, 0, { text: "선택x", value: null });
        },
        (err) => {
          console.error(err);
        }
      );
    },
    getAttraction() {
      if (this.keyword.trim().length === 0) {
        alert("키워드는 필수입니다");
        this.keyword = "";
        this.$refs["keywordTextArea"].focus();
        return;
      }
      const param = JSON.parse(JSON.stringify(basicParam));
      param["keyword"] = this.keyword;
      param["areaCode"] = this.sido;
      param["sigunguCode"] = this.gugun;
      param["keyword"] = this.keyword;
      param["numOfRows"] = "9999";
      listAttraction(
        param,
        ({ data }) => {
          // console.log(data);
          if (data.response.body.numOfRows == 0) {
            alert("검색 결과가 없습니다!");
            return;
          }
          const res = data.response.body.items.item.map((item) => {
            if (item.firstimage === "") {
              item.firstimage =
                "https://cdn.imweb.me/upload/S201910012ff964777e0e3/62f9a36ea3cea.jpg";
            }
            this.getDetail(item.contentid, item);

            return item;
          });
          // console.log(res);

          // this.attractions = data.response.body.items.item;
          this.attractions = res;
          // console.log(this.attractions);
        },
        (err) => {
          alert("다시 검색해주세요");
          this.keyword = "";
          this.$refs["keywordTextArea"].focus();
          console.error(err);
        }
      );
    },

    // dialog 관련
    open(item) {
      this.editedIndex = item.contentid;
      this.dialog = true;
    },
    close() {
      this.dialog = false;
      this.select = null;
      this.$nextTick(() => {
        this.editedIndex = -1;
      });
    },
    save() {
      console.log("ind" + this.editedIndex);
      console.log("dodo" + this.select);
      const ti = this.select.tripId;
      const ei = this.editedIndex;
      getAttractionDetail(
        this.editedIndex,
        ({ data }) => {
          this.insertingPlanLen = data.length;

          if (Object.keys(data).length > 0) {
            // TODO: 전송
            this.addPlan({
              tripId: ti,
              attractionId: ei,
            });
            // alert("추가되었습니다");
          } else {
            alert("관광지 사정으로 추가할 수 없는 관광지입니다.");
          }
        },
        (err) => {
          console.log(err);
        }
      );

      this.close();
    },
  },
  watch: {
    sido: function (item) {
      if (item == null) {
        this.guguns = [];
        return;
      }
      // alert(item);
      const gugunParam = JSON.parse(JSON.stringify(basicParam));
      gugunParam["areaCode"] = item;
      // console.log(basicParam); //const gugunParam = basicParam 이렇게 했을때 주소복사되나 확인용
      this.getGugun(gugunParam);
      this.gugun = "";
    },
    dialog(val) {
      val || this.close();
    },
  },
  created() {
    this.getSido(basicParam);
    this.getTrips({
      userId: this.userInfo.id,
      orderBy: "create_time",
      orderDir: "desc",
    });
  },
};
</script>

<style scope></style>
