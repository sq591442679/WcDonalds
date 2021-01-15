<template>
  <div id="registerBackground">
    <div class="front">
      <h1 class="centerAlign">买当劳™订餐系统</h1>
      <div class="radio">
        选择您的身份：
        <el-radio-group v-model="userType">
          <el-radio :label="1">顾客</el-radio>
<!--          <el-radio :label="2">员工</el-radio>-->
          <el-radio :label="2">管理员</el-radio>
        </el-radio-group>
      </div>
      <el-input v-model="userName" placeholder="输入您的用户名" class="myInput"></el-input>
      <el-input v-model="userPassword" placeholder="输入您的密码" show-password class="myInput"></el-input>
      <el-button id="loginButton" v-on:click="login(userType, userName, userPassword)" type="primary">登录</el-button>
      <el-button id="registerButton" v-on:click="jumpToRegister">注册</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      userName: '',
      userPassword: '',
      userType: 1 //1:顾客，2：管理员
    }
  },
  methods: {
    login(userType, userName, userPassword) {
      if (userName === '' || userPassword === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/login',
            {
              'userType': userType.toString(),
              'username': userName,
              'password': userPassword
            }
          )
        .then(
          (response) => {
            if (response.data === 1) {
              if (userType === 1) { //顾客
                window.location = '/customer';
              }
              else if (userType === 2) { //管理员
                window.location = '/administrator'
              }
            }
            else if (response.data === -1) { //密码错误
              this.$message.error('密码错误')
            }
            else {
              this.$message.error('用户不存在');
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
  position: fixed;
  background-size: 100% 100%;
}

.front {
  position: absolute;
  background: rgba(255, 255, 255, 0.8);
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  padding: 10px 15px 10px;
  border-radius: 10px;
}

#loginButton {
  margin-top: 10px;
  position: relative;
  left: 150px;
}

#registerButton {
  margin: 10px;
  position: relative;
  right: 150px;
}

.centerAlign {
  text-align: center;
}

.radio {
  padding-left: 15px;
}

.myInput {
  margin-top: 10px;
  width: 400px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
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