<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <template #header>My Page</template>
          <hr class="my-4" />
          <b-container class="mt-4">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">아이디</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.userid }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이름</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.username
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">이메일</b-col
              ><b-col cols="4" align-self="start">{{ userInfo.email }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">가입일</b-col
              ><b-col cols="4" align-self="start">{{
                userInfo.joindate
              }}</b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" href="#" class="mr-1">정보수정</b-button>
          <b-button variant="danger" @click="openModal">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
    <member-delete ref="deleteModal" @deleteUser="deleteUser" />
  </b-container>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import MemberDelete from "@/components/user/MemberDelete.vue";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: { MemberDelete },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    deleteUser() {
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      alert("회원탈퇴가 완료되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "home" });
    },
    openModal() {
      this.$refs.deleteModal.$refs.modal.show();
    },
  },
};
</script>

<style></style>
