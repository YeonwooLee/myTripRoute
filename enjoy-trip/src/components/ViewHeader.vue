<template>
  <v-container
    fluid
    class="d-flex fixedHeight justify-center align-center mb-1 rounded-lg"
  >
    <h1>{{ type }}</h1>
  </v-container>
</template>

<script>
export default {
  name: "ViewHeader",
  props: {
    type: String,
  },
};
</script>

<style scope></style>
