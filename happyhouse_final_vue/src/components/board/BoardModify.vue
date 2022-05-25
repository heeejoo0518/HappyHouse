<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col cols="2"></b-col>
      <b-col cols="8">
        <b-jumbotron bg-variant="white" header-level="4">
          <template #header>글 수정</template>
          <hr class="my-4" />
          <board-input-item type="modify" />
          <hr class="my-4" />
        </b-jumbotron>
      </b-col>
      <b-col cols="2"></b-col>
    </b-row>
  </b-container>
</template>

<script>
import BoardInputItem from "@/components/board/item/BoardInputItem.vue";

export default {
  name: "BoardModify",
  components: {
    BoardInputItem,
  },
};
</script>

<style></style>
