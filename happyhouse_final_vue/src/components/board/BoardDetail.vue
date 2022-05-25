<!-- @format -->

<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle" size="sm"
          >목록</b-button
        >
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="deleteArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3 class='mb-4 mt-1'>${article.subject}</h3><div class='text-right'><strong>${article.userid}</strong>  ${article.regtime}</div>`"
          class="mb-2"
          border-variant="white"
          no-body
        >
          <b-card-body class="text-left" style="min-height: 400px">
            <div v-html="message"></div>
          </b-card-body>
        </b-card>
        <hr class="my-4" />

        <div class="">
          <b-card>
            <b-row>
              <b-col cols="10">
                <b-form-textarea
                  id="textarea"
                  v-model="comment"
                  placeholder="Enter something..."
                  rows="3"
                  max-rows="6"
                ></b-form-textarea>
              </b-col>
              <b-col cols="2">
                <button class="btn btn-outline-primary" @click="createComment">
                  등록
                </button></b-col
              >
            </b-row>
          </b-card>
          <b-card
            class="text-left"
            v-for="comment in article.comments"
            :key="comment.commentno"
          >
            <b-card-text>
              <b>{{ comment.userid }}</b>
              {{ comment.regtime }}
              <span>
                <button
                  @click="deleteComment(comment.commentno)"
                  class="btn btn-outline-danger btn-sm"
                >
                  삭제
                </button>
              </span>
            </b-card-text>
            <b-card-text>{{ comment.content }}</b-card-text>
          </b-card>
        </div>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
// import moment from "moment";
import {
  getArticle,
  deleteArticle,
  listComments,
  createComment,
  deleteComment,
} from "@/api/board";

export default {
  name: "BoardDetail",
  data() {
    return {
      article: {},
      comment: "",
      userid: "ssafy",
    };
  },
  computed: {
    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
  },
  created() {
    this.listComments();

    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제시 에러발생!!", error);
      },
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "boardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "boardModify",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    deleteArticle() {
      if (confirm("정말로 삭제?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "boardList" });
        });
      }
    },
    listComments() {
      listComments(
        this.$route.params.articleno,
        (response) => {
          this.article = response.data;
        },
        (error) => {
          console.log(error);
        },
      );
    },
    createComment() {
      createComment(
        {
          articleno: this.article.articleno,
          userid: this.userid,
          content: this.comment,
        },
        (response) => {
          if (response.data === "success") {
            this.listComments();
            this.comment = "";
          }
        },
        (error) => {
          console.log("댓글 등록시 에러발생!!", error);
        },
      );
    },

    deleteComment(commentno) {
      deleteComment(
        commentno,
        (response) => {
          if (response.data === "success") {
            this.listComments();
          }
        },
        (error) => {
          console.log("댓글 등록시 에러발생!!", error);
        },
      );
    },
  },
  // filters: {
  //   dateFormat(regtime) {
  //     return moment(new Date(regtime)).format("YY.MM.DD hh:mm:ss");
  //   },
  // },
};
</script>

<style>
.card-header {
  background-color: #fcf9f2;
}
</style>
