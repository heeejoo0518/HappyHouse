<template>
  <b-container>
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-row>
        <b-col cols="2"></b-col>
        <b-col cols="3" align-self="end" class="subject"
          ><label>아이디</label> </b-col
        ><b-col cols="5" align-self="start" class="item text-left">{{
          user.userid
        }}</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="2"></b-col>
        <b-col cols="3" align-self="end" class="subject"
          ><label>비밀번호</label></b-col
        ><b-col cols="5" align-self="start" class="item"
          ><b-input
            type="password"
            :state="pwdState"
            v-model="user.userpwd"
          ></b-input
        ></b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row v-if="this.type === 'modify'">
        <b-col cols="2"></b-col>
        <b-col cols="3" align-self="end" class="subject"
          ><label>새로운 비밀번호</label></b-col
        ><b-col cols="5" align-self="start" class="item"
          ><b-input type="password" v-model="newpwd"></b-input
        ></b-col>
        <b-col cols="2"></b-col>
      </b-row>

      <b-row>
        <b-col cols="2"></b-col>
        <b-col cols="3" align-self="end" class="subject"
          ><label>이름</label></b-col
        ><b-col cols="5" align-self="start" class="item"
          ><b-input type="text" v-model="user.username"></b-input
        ></b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="2"></b-col>
        <b-col cols="3" align-self="end" class="subject"
          ><label>이메일</label></b-col
        ><b-col cols="5" align-self="start" class="item"
          ><b-input type="email" v-model="user.email"></b-input
        ></b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </form>
    <hr class="my-4" />
    <b-button
      v-if="this.type === 'modify'"
      variant="outline-primary"
      class="mr-1"
      @click="handleSubmit"
      >정보수정</b-button
    ><b-button
      variant="outline-primary"
      href="#"
      class="mr-1"
      @click="handleSubmit"
      v-else
      >회원가입</b-button
    >
  </b-container>
</template>

<script>
import { checkUser, register, modify } from "@/api/member";
import { mapState, mapMutations } from "vuex";

export default {
  name: "MemberInputItem",
  data() {
    return {
      newpwd: "",
      pwdState: null,
      user: { userid: "", userpwd: "", username: "", email: "" },
    };
  },
  props: {
    type: { type: String },
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),
  },
  created() {
    if (this.type === "modify") {
      this.user.userid = this.userInfo.userid;
      this.user.username = this.userInfo.username;
      this.user.email = this.userInfo.email;
    }
  },
  methods: {
    ...mapMutations("memberStore", ["SET_USER_INFO"]),

    checkFormValidty() {
      if (!this.$refs.form.checkValidity()) return false;
      return true;
    },
    async handleSubmit() {
      if (!this.checkFormValidty()) {
        return;
      }
      if (this.type === "modify") {
        this.pwdState = null;
        await checkUser(
          { userid: this.user.userid, userpwd: this.user.userpwd },
          ({ data }) => {
            this.pwdState = data.message === "success";
          },
          (error) => {
            console.log(error);
          },
        );
      }

      //type 확인해서 맞는 함수 호출하기
      if (this.type === "register") {
        this.registUser();
      } else if (this.pwdState) {
        this.modifyUser();
      }
    },
    registUser() {
      //회원등록
      register();
      //정상적으로 끝났다면 로그인페이지로
      this.$router.push("/user/signin");
    },
    modifyUser() {
      modify(
        this.user,
        ({ data }) => {
          if (data.message === "success") {
            this.SET_USER_INFO(data.userInfo);
            //정상적으로 끝났다면 회원정보페이지로
            alert("정보 수정 완료");
            this.$router.push("/user/mypage");
          }
        },
        (e) => {
          console.log(e);
          alert("정보 수정 실패");
        },
      );
    },
  },
};
</script>

<style>
.subject {
  font-weight: bold;
  text-align: right;
  margin: 0.3rem;
}
.item {
  margin: 0.3rem;
}
</style>
