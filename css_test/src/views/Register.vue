<template>
  <div id="registerBackground">
    <div>
    </div>
    <div class="front">
      <h1 class="centerAlign">注册</h1>
<!--      <div class="radio">-->
<!--        选择您的身份：-->
<!--        <el-radio-group v-model="userType">-->
<!--          <el-radio :label="1">顾客</el-radio>-->
<!--          <el-radio :label="2">员工</el-radio>-->
<!--          <el-radio :label="3">管理员</el-radio>-->
<!--        </el-radio-group>-->
<!--      </div>-->
      <el-input v-model="userName" placeholder="输入您的用户名" class="myInput"></el-input>
      <el-input v-model="userPassword" placeholder="输入您的密码" show-password class="myInput"></el-input>
      <el-button id="registerButton" v-on:click="register(userName, userPassword)" type="primary">注册</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Register",
  data() {
    return {
      userName: '',
      userPassword: '',
    }
  },
  methods: {
    register(userName, userPassword) {
      if (userName === '' || userPassword === '') {
        alert('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/regist',
            {
              'username': userName,
              'password': userPassword,
            }
          )
          .then(
            (response) => {
              if (response.data === 1) {
                alert('注册成功！');
                window.location = '/customer';
              }
              else { //重名
                this.$message.error('用户名已存在');
              }
            }
          )
      }
    },
    jumpToRegister() {
      window.location = '/register';
    }
  }
}
</script>

<style>
#registerBackground {
  background: url("../images/seaside_background.png");
  width: 100%;
  height: 100%;
  position: absolute;
  background-size: 100% 100%;
}

#registerButton {
  margin: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}

.centerAlign {
  text-align: center;
}

.radio {
  padding-left: 15px;
}

.myInput {
  width: 400px;
}
</style>

/*.background {*/
/*  border: 0;*/
/*  margin: 0;*/
/*  padding: 0;*/
/*  background: url("../images/seaside_background.png");*/
/*  width: 100%;*/
/*  height: 100%;  !**宽高100%是为了图片铺满屏幕 *!*/
/*  background-size: 100% 100%;*/
/*  z-index: -1;*/
/*  position: fixed;*/
/*}*/