<template>
  <v-container>
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
                :readonly="isLogin"
                id="userFormId"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user_nm"
                label="이름*"
                :rules="user_nm_rule"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user_pw"
                label="비밀번호*"
                type="password"
                :rules="user_pw_rule"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user_pw_chk"
                label="비밀번호 확인*"
                type="password"
                :rules="user_pw_rule2"
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-select
                v-model="user_auth"
                label="권한*"
                :items="authList"
                item-text="name"
                item-value="value"
                return-object
                :rules="user_auth_rule"
                disabled
              ></v-select>
            </v-col>
            <v-col cols="12">
              <v-text-field
                v-model="user_email"
                label="이메일"
                :rules="user_email_rule"
              ></v-text-field>
            </v-col>
          </v-row>
        </v-form>
        <small class="red--text">*표시는 반드시 입력해야하는 항목입니다.</small>
      </v-card-text>
      <v-card-actions>
        <v-spacer></v-spacer>
        <v-btn
          class="ma-2"
          raised
          depressed
          color="primary"
          @click="regist"
          v-if="type === 'regist'"
        >
          <v-icon left>mdi-check</v-icon> 가입
        </v-btn>
        <v-btn
          class="ma-2"
          raised
          depressed
          color="primary"
          @click="modify"
          v-if="type === 'modify'"
        >
          <v-icon left>mdi-check</v-icon> 수정
        </v-btn>
        <v-btn
          class="ma-2"
          raised
          depressed
          color="error"
          @click="withdraw"
          v-if="type === 'modify'"
        >
          <v-icon left>mdi-close</v-icon> 탈퇴
        </v-btn>
      </v-card-actions>
    </v-card>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

export default {
  name: "UserWriteForm",
  props: {
    type: String,
  },
  data() {
    return {
      dialog: false,
      state: "ins",
      authList: [
        { name: "일반 사용자", value: "NORMAL" },
        { name: "관리자", value: "ADMIN" },
      ],
      user_id: "",
      user_id_rule: [
        (v) => !!v || "아이디는 필수 입력사항입니다.",
        (v) =>
          /^[a-zA-Z0-9]*$/.test(v) || "아이디는 영문+숫자만 입력 가능합니다.",
        (v) =>
          !(v && v.length >= 15) || "아이디는 15자 이상 입력할 수 없습니다.",
      ],
      user_nm: "",
      user_nm_rule: [
        (v) => !!v || "이름은 필수 입력사항입니다.",
        (v) => !(v && v.length >= 30) || "이름은 30자 이상 입력할 수 없습니다.",
        (v) =>
          !/[~!@#$%^&*()_+|<>?:{}]/.test(v) ||
          "이름에는 특수문자를 사용할 수 없습니다.",
      ],
      user_pw: "",
      user_pw_chk: "",
      user_pw_rule: [
        (v) =>
          this.state === "ins"
            ? !!v || "패스워드는 필수 입력사항입니다."
            : true,
        (v) =>
          !(v && v.length >= 30) || "패스워드는 30자 이상 입력할 수 없습니다.",
      ],
      user_pw_rule2: [
        (v) =>
          this.state === "ins"
            ? !!v || "패스워드는 필수 입력사항입니다."
            : true,
        (v) =>
          !(v && v.length >= 30) || "패스워드는 30자 이상 입력할 수 없습니다.",
        (v) => v === this.user_pw || "패스워드가 일치하지 않습니다.",
      ],
      user_auth: { name: "일반 사용자", value: "NORMAL" },
      user_auth_rule: [(v) => !!v || "권한은 필수 선택 사항입니다."],
      user_email: "",
      user_email_rule: [
        (v) => !!v || "이메일은 필수 입력사항입니다.",
        (v) =>
          /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/.test(v) ||
          "이메일 형식을 확인해주세요.",
      ],
    };
  },
  computed: {
    ...mapState("userStore", ["userInfo", "isLogin"]),
  },
  created() {
    if (this.type === "modify") {
      this.user_id = this.userInfo.id;
      this.user_nm = this.userInfo.name;
      this.user_pw = this.userInfo.pw;
      this.user_auth.value = this.userInfo.grade;
      this.user_email = this.userInfo.email;
    }
  },
  methods: {
    ...mapActions("userStore", [
      "userRegist",
      "userUpdate",
      "userRemove",
      "getUserInfo",
    ]),
    async regist() {
      const validate = this.$refs.form.validate();
      if (validate) {
        if (confirm("등록하시겠습니까?")) {
          const params = {
            id: this.user_id,
            name: this.user_nm,
            pw: this.user_pw,
            grade: this.user_auth.value,
            email: this.user_email,
          };
          console.log(params);
          await this.userRegist(params);
        }
      }
    },
    async modify() {
      console.log("수정");
      const validate = this.$refs.form.validate();
      if (validate) {
        if (confirm("수정하시겠습니까?")) {
          const params = {
            id: this.user_id,
            name: this.user_nm,
            pw: this.user_pw,
            grade: this.user_auth.value,
            email: this.user_email,
          };
          console.log(params);
          await this.userUpdate(params);

          let token = sessionStorage.getItem("access-token");
          if (this.isLogin) {
            await this.getUserInfo(token);
            this.$router.push({ name: "Home" });
            alert("정보 수정에 성공했습니다.");
            // } else {
            //   alert("로그인에 실패하였습니다.");
            //   this.$refs.form.reset();
          }
        }
      }
    },
    withdraw() {
      console.log("탈퇴");
      let pw = prompt("현재 비밀번호를 입력하세요");
      console.log(pw);
      this.userRemove({ id: this.userInfo.id, pw: pw });
    },
  },
};
</script>

<style scoped></style>
