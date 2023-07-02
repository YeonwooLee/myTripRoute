<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn fab x-large outlined v-bind="attrs" v-on="on" v-if="type === 'create'">
          <v-icon>mdi-pencil</v-icon>
        </v-btn>

        <v-btn color="orange" text dark v-bind="attrs" v-on="on" v-if="type === 'modify'">
          편집
        </v-btn>
      </template>

      <v-card>
        <v-card-title>
          <span class="text-h5" v-if="type === 'create'"> 새 여행 생성 </span>
          <span class="text-h5" v-if="type === 'modify'"> 여행명 수정 </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-form ref="form">
              <!-- <v-col cols="12" ref="form"> -->
              <v-text-field
                label="여행명*"
                required
                counter="45"
                :rules="trip_name_rule"
                v-model="trip.tripName"
              ></v-text-field>
              <!-- </v-col> -->
            </v-form>
          </v-container>
        </v-card-text>

        <!-- <v-divider></v-divider> -->

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="create()" v-if="type === 'create'">생성</v-btn>
          <v-btn color="blue darken-1" text @click="modify()" v-if="type === 'modify'">수정</v-btn>
          <v-btn color="blue darken-1" text @click="remove()" v-if="type === 'modify'">삭제</v-btn>
          <v-btn color="blue darken-1" text @click="reset()">닫기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "TripModify",
  props: {
    tripProp: Object,
    type: String,
  },
  computed: {
    ...mapState("userStore", ["userInfo"]),
  },
  data() {
    return {
      dialog: false,
      trip: {
        tripId: "",
        tripName: "",
      },
      trip_name_rule: [
        (v) => !!v || "여행명은 필수 입력사항입니다.",
        (v) => v.length <= 45 || "여행명은 45자까지 작성할 수 있습니다",
      ],
    };
  },
  created() {
    this.reset();
  },
  methods: {
    ...mapActions("tripStore", ["deleteTrip", "getTrips", "makeTrip", "modifyTrip"]),
    async create() {
      if (this.$refs.form.validate()) {
        console.log(`생성 ${this.trip.tripName}`);
        await this.makeTrip({
          tripName: this.trip.tripName,
          userId: this.userInfo.id,
        });

        this.dialog = false;

        await this.getTrips({
          userId: this.userInfo.id,
          orderBy: "create_time",
          orderDir: "desc",
        });
      }
    },
    async modify() {
      if (this.$refs.form.validate()) {
        console.log(`수정 ${this.trip.tripId} to ${this.trip.tripName}`);
        await this.modifyTrip({
          tripId: this.trip.tripId,
          tripName: this.trip.tripName,
          userId: this.userInfo.id,
        });

        this.dialog = false;

        await this.getTrips({
          userId: this.userInfo.id,
          orderBy: "create_time",
          orderDir: "desc",
        });
      }
    },
    async remove() {
      if (confirm("삭제하시겠습니까?")) {
        console.log(`삭제 ${this.trip.tripId}`);
        await this.deleteTrip({
          tripId: this.trip.tripId,
          userId: this.userInfo.id,
        });

        await this.getTrips({
          userId: this.userInfo.id,
          orderBy: "create_time",
          orderDir: "desc",
        });
      }
    },
    reset() {
      this.dialog = false;
      if (this.type === "create") {
        this.trip.tripId = "";
        this.trip.tripName = "";
      } else if (this.type === "modify") {
        this.trip.tripId = this.tripProp.tripId;
        this.trip.tripName = this.tripProp.tripName;
      }
    },
  },
};
</script>

<style></style>
