<template>
  <b-container>
    <form ref="form" @submit.stop.prevent="handleSubmit">
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>아이디</label>
        </b-col>
        <b-col cols="8" align-self="start" class="item text-left">{{
          user.userid
        }}</b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>비밀번호</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input
            type="password"
            :state="pwdState"
            area-describedby="input-pw-feedback"
            v-model.trim="user.userpwd"
          ></b-form-input
          ><b-form-invalid-feedback id="input-pwd-feedback">
            현재 비밀번호를 정확하게 입력해주세요.
          </b-form-invalid-feedback>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>새로운 비밀번호</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input
            type="password"
            :state="inputPwdState"
            v-model.trim="newpwd"
          ></b-form-input
          ><b-form-invalid-feedback id="input-pwd-feedback">
            비밀번호는 영어와 숫자를 포함한 6글자 이상이어야 합니다.
          </b-form-invalid-feedback>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>이름</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input type="text" v-model.trim="user.username"></b-form-input
        ></b-col>
        <b-col cols="1"></b-col>
      </b-row>
      <b-row>
        <b-col cols="3" align-self="center" class="subject"
          ><label>이메일</label></b-col
        ><b-col cols="8" align-self="start" class="item"
          ><b-form-input
            type="email"
            :state="inputEmailState"
            aria-describedby="input-email-feedback"
            v-model.trim="user.email"
          ></b-form-input
          ><b-form-invalid-feedback id="input-email-feedback">
            정확한 이메일을 입력해주세요.
          </b-form-invalid-feedback>
        </b-col>
        <b-col cols="2"></b-col>
      </b-row>
    </form>
    <hr class="my-4" />
    <b-button variant="outline-primary" class="mr-1" @click="handleSubmit"
      >정보수정</b-button
    >
  </b-container>
</template>

<script>
import { checkUser, register, modify } from "@/api/member";
import { mapState, mapMutations } from "vuex";

export default {
  name: "MemberModifyItem",
  data() {
    return {
      newpwd: "",
      pwdState: null,
      user: { userid: "", userpwd: "", username: "", email: "" },
    };
  },
  computed: {
    ...mapState("memberStore", ["userInfo"]),

    //새 비밀번호 체크
    inputPwdState() {
      if (!this.newpwd) return null;
      let pattern1 = /[0-9]/; //숫자
      let pattern2 = /[a-zA-Z]/; //영어
      return (
        this.newpwd.length >= 6 &&
        pattern1.test(this.newpwd) &&
        pattern2.test(this.newpwd)
      );
    },

    inputEmailState() {
      if (!this.user.email) return null;
      if (this.user.email === this.userInfo.email) return null;
      let pattern =
        /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
      return pattern.test(this.user.email);
    },
  },
  created() {
    this.user.userid = this.userInfo.userid;
    this.user.username = this.userInfo.username;
    this.user.email = this.userInfo.email;
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
      this.pwdState = null;
      await checkUser(
        { userid: this.user.userid, userpwd: this.user.userpwd },
        ({ data }) => {
          this.pwdState = data.message === "success";
          if (this.pwdState) this.modifyUser();
        },
        (error) => {
          console.log(error);
        },
      );
    },
    registUser() {
      //회원등록
      register();
      //정상적으로 끝났다면 로그인페이지로
      this.$router.push("/user/signin");
    },
    modifyUser() {
      modify(
        {
          userid: this.user.userid,
          userpwd: this.newpwd,
          username: this.user.username,
          email: this.user.email,
        },
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
  margin: 0.2rem;
}
.item {
  margin: 0.2rem;
}
</style>
