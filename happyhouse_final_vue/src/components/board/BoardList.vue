<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <h3 class="underline-hotpink">QnA</h3>
    <b-row class="mb-1">
      <b-col class="text-right">
        <b-button variant="outline-primary" @click="moveWrite()" size="sm"
          >글쓰기</b-button
        >
      </b-col>
    </b-row>
    <b-row>
      <b-col>
        <b-table
          hover
          :items="articles"
          :fields="fields"
          @row-clicked="viewArticle"
          :per-page="perPage"
          :current-page="currentPage"
        >
        </b-table>
      </b-col>
    </b-row>
    <div class="overflow-auto">
      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
        align="center"
      ></b-pagination>
    </div>
  </b-container>
</template>

<script>
import { listArticle, getTotalCount } from "@/api/board.js";

export default {
  name: "BoardList",
  data() {
    return {
      articles: [],
      perPage: 10,
      currentPage: 1,
      rows: 1,
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
      ],
    };
  },
  created() {
    listArticle(
      (response) => {
        this.articles = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
    getTotalCount(
      (response) => {
        this.rows = response.data;
      },
      (error) => {
        console.log(error);
      },
    );
  },
  methods: {
    moveWrite() {
      this.$router.push({ name: "boardRegister" });
    },
    viewArticle(article) {
      this.$router.push({
        name: "boardDetail",
        params: { articleno: article.articleno },
      });
    },
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
