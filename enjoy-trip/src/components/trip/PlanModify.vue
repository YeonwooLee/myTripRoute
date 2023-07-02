<template>
  <div class="text-center">
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn color="red lighten-2" dark v-bind="attrs" v-on="on">
          편집
        </v-btn>
      </template>

      <v-card>
        <v-card-title>
          <span class="text-h5">
            {{ this.plan.attractionTitle }}
          </span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="6">
                <v-text-field
                  label="여행번호*"
                  required
                  :value="this.plan.planId"
                  readonly
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  label="관광지명*"
                  required
                  :value="this.plan.attractionTitle"
                  readonly
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  label="여행일*"
                  :value="this.plan.planDate"
                  :rules="number_rule"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="6">
                <v-text-field
                  label="여행 순서*"
                  :value="this.plan.planOrder"
                  :rules="number_rule"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-text-field
                  label="예산*"
                  :value="this.plan.planBudget"
                  :rules="number_rule"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <v-textarea
                  label="일정 설명"
                  counter="2000"
                  :rules="plan_desc_rule"
                  :value="this.plan.planDesc"
                  outlined
                ></v-textarea>
              </v-col>
            </v-row>
          </v-container>
          <small>*표시는 반드시 입력해야하는 항목입니다.</small>
        </v-card-text>

        <!-- <v-divider></v-divider> -->

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="blue darken-1" text @click="modify(plan.planId)"
            >수정</v-btn
          >
          <v-btn color="blue darken-1" text @click="remove(plan.planId)"
            >삭제</v-btn
          >
          <v-btn color="blue darken-1" text @click="dialog = false">닫기</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapActions } from "vuex";
export default {
  name: "PlanModify",
  props: {
    planProp: Object,
  },
  data() {
    return {
      dialog: false,
      plan: null,
      number_rule: [(v) => /^[0-9]*$/.test(v) || "숫자만 입력 가능합니다."],
      plan_desc_rule: [
        (v) => {
          if (v) {
            return v.length <= 2000 || "설명은 2000자까지 작성할 수 있습니다";
          }
          return true;
        },
      ],
      originPlan: null,
    };
  },
  created() {
    this.plan = this.planProp;
  },
  methods: {
    ...mapActions("planStore", ["deletePlan"]),
    modify(id) {
      console.log(`수정 ${id}`);
    },
    async remove(id) {
      if (confirm("삭제하시겠습니까?")) {
        console.log(`삭제 ${id}`);
        await this.deletePlan({
          planId: id,
        });
        this.dialog = false;
        this.$router.go();
      }
    },
  },
};
</script>

<style></style>
