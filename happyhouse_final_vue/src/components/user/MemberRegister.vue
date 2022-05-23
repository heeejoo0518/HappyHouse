<template>
  <b-container class="mt-4">
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <b-jumbotron bg-variant="white" header-level="4">
          <template #header>회원가입</template>
          <hr class="my-4" />
          <member-register-item />
        </b-jumbotron>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import MemberRegisterItem from "@/components/user/item/MemberRegisterItem.vue";
export default {
  name: "MemberRegister",
  components: {
    MemberRegisterItem,
  },
};
</script>

<style></style>
