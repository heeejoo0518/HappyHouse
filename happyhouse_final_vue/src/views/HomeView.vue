<template>
  <b-row>
    <b-col cols="2"></b-col>
    <b-col cols="">
      <b-row>
        <img src="@/assets/main.jpg" />
      </b-row>

      <b-row>
        <img src="@/assets/apartment.jpg" />
      </b-row>
      <b-row>
        <img src="@/assets/trot.jpg" />
      </b-row>
    </b-col>
    <b-col cols="1"></b-col>
  </b-row>
</template>

<script>
export default {
  name: "HomeView",
  data() {
    return {};
  },
  methods: {},
};
</script>
<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
.slide {
  height: 80vh;
}
</style>
