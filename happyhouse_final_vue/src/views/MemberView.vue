<template>
  <b-container class="bv-example-row mt-3 text-center">
    <!--     <h3 class="underline-steelblue">
      <b-icon icon="person-lines-fill"></b-icon> Member Service
    </h3> -->
    <b-row class="text-center" align-v="center">
      <router-view></router-view>
    </b-row>
  </b-container>
</template>

<script>
export default {
  name: "MemberView",
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
</style>
