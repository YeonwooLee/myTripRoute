<template>
  <v-container class="d-flex justify-space-between">
    <div class="" style="width: 20%">
      <v-img
        lazy-src="https://picsum.photos/id/11/10/6"
        max-height="150"
        max-width="100"
        src="@/assets/logo.png"
        style=""
        @click="goMain"
      >
      </v-img>
    </div>
    <div class="d-flex justify-center" style="width: 50%">
      <v-row justify="center" no-gutters>
        <router-link to="/attraction" class="header-btn v-btn"
          >관광지 찾기</router-link
        >
        <router-link to="/trip" class="header-btn v-btn"
          >여행루트 생성기</router-link
        >
        <router-link to="/board" class="header-btn v-btn">게시판</router-link>
      </v-row>
    </div>
    <div class="d-flex justify-end" style="width: 30%" v-if="!isLogin">
      <router-link :to="{ name: 'userLogin' }" class="header-btn v-btn">
        로그인</router-link
      >
      <router-link :to="{ name: 'userRegist' }" class="header-btn v-btn"
        >회원가입</router-link
      >
    </div>
    <div class="d-flex justify-end" style="width: 30%" v-if="isLogin">
      <router-link :to="{ name: 'userMypage' }" class="header-btn v-btn"
        >마이페이지</router-link
      >
      <a href="#" class="header-btn v-btn" @click.prevent="logout">로그아웃</a>
    </div>
  </v-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
export default {
  name: "TheHeader",
  data() {
    return {
      src: require("@/assets/main.jpg"),
    };
  },
  computed: {
    ...mapState("userStore", ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapActions("userStore", ["userLogout"]),
    goMain() {
      if (window.location.pathname != "/") {
        this.$router.push("/");
      }
    },
    logout() {
      console.log(this.userInfo);
      this.userLogout(this.userInfo.id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style scope>
.header-btn {
  margin-left: 3%;
}
</style>
