<template>
  <b-container class="mt-4">
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <b-jumbotron bg-variant="white">
          <template #header>My Page</template>
          <hr class="my-4" />
          <member-input-item />
        </b-jumbotron>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import MemberInputItem from "@/components/user/item/MemberModifyItem.vue";

export default {
  name: "MemberModify",
  components: { MemberInputItem },
};
</script>

<style></style>
