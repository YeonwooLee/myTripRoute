<template>
  <v-card class="flex" flat tile>
    <v-card-title class="teal">
      <strong class="subheading"
        >Travel far, travel wide, travel deep within.</strong
      >

      <v-spacer></v-spacer>
    </v-card-title>

    <v-card-text class="py-2 white--text text-center">
      {{ new Date().getFullYear() }} — <strong>삼성청년아카데미</strong>
    </v-card-text>
  </v-card>
</template>

<script>
export default {
  name: "TheFooter",
};
</script>

<style scope></style>
