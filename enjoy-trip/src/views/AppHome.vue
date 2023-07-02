<template>
  <v-container>
    <v-app id="inspire">
      <v-carousel hide-delimiters>
        <v-carousel-item
          v-for="(item, i) in items"
          :key="i"
          :src="item.src"
          reverse-transition="fade-transition"
          transition="fade-transition"
        ></v-carousel-item>
      </v-carousel>
      <v-card class="mx-auto" width="1820" height="400" outlined>
        <v-list-item three-line>
          <v-list-item-content>
            <div class="text-overline mb-5">MY TRIP ROUTE</div>
            <v-list-item-title class="text-h5 mb-1">
              Travel far, travel wide, travel deep within.
            </v-list-item-title>
            <v-list-item-subtitle>Adventure awaits.</v-list-item-subtitle>
          </v-list-item-content>

          <!-- <v-list-item-avatar tile size="80" color="grey"></v-list-item-avatar> -->
        </v-list-item>
      </v-card>
    </v-app>
  </v-container>
</template>

<script>
export default {
  name: "AppHome",

  data() {
    return {
      items: [
        {
          src: require("@/assets/main.jpg"),
        },
        {
          src: require("@/assets/main2.jpg"),
        },
      ],
    };
  },
};
</script>

<style scope></style>
