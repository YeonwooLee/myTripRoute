<template>
  <v-container>
    <view-header type="로그인"></view-header>
    <v-card>
      <v-card-text>
        <v-form ref="form" lazy-validation>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="user_id"
                label="아이디*"
                :rules="user_id_rule"
                :disabled="state == 'ins' ? false : true"
                required
                id="loginId"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user_pw"
                label="비밀번호*"
                type="password"
                :rules="user_pw_rule"
                @keypress.enter="save"
                id="loginPw"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          class="ma-2"
          raised
          depressed
          color="primary"
          @click.prevent="save"
        >
          <v-icon left>mdi-check</v-icon> 로그인
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import ViewHeader from "@/components/ViewHeader.vue";
import { mapState, mapActions } from "vuex";

export default {
  name: "UserLogin",
  components: {
    ViewHeader,
  },
  data() {
    return {
      dialog: false,
      state: "ins",
      user_id: "",
      user_id_rule: [(v) => !!v || "아이디는 필수 입력사항입니다."],
      user_pw: "",
      user_pw_rule: [
        (v) =>
          this.state === "ins"
            ? !!v || "패스워드는 필수 입력사항입니다."
            : true,
      ],
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["userConfirm", "getUserInfo"]),
    movePage() {
      this.$router.push({ name: "join" });
    },
    async save() {
      const validate = this.$refs.form.validate();
      if (validate) {
        const params = {
          id: this.user_id,
          pw: this.user_pw,
        };
        await this.userConfirm(params);
        let token = sessionStorage.getItem("access-token");
        console.log("1. confirm() token >> " + token);
        if (this.isLogin) {
          await this.getUserInfo(token);
          console.log("4. confirm() userInfo :: ", this.userInfo);
          this.$router.push({ name: "Home" });
        } else {
          alert("로그인에 실패하였습니다.");
          this.$refs.form.reset();
        }
      }
    },
  },
};
</script>

<style scoped></style>
