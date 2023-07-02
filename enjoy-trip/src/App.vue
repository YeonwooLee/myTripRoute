<template>
  <v-app>
    <v-app-bar color="white" elevation="0" hide-on-scroll app>
      <the-header></the-header>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>

    <v-footer padless dark>
      <the-footer></the-footer>
    </v-footer>
  </v-app>
</template>

<script>
import TheFooter from "./components/TheFooter.vue";
import TheHeader from "./components/TheHeader.vue";
export default {
  components: {
    TheHeader,
    TheFooter,
  },
  name: "App",
  data: () => ({
    //
  }),
};
</script>

<style>
@font-face {
  font-family: "Pretendard-Regular";
  src: url("https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff")
    format("woff");
  font-weight: 400, 500, 600;
  font-style: normal;
}
* {
  font-family: "Pretendard-Regular";
}
.fixedHeight {
  height: 200px;
  background-color: lightblue;
}
</style>
