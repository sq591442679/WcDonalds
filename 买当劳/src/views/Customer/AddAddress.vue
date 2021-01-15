<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">添加取餐信息</h1>
      <el-input v-model="name" placeholder="请输入联系人姓名" class="myInput"></el-input>
      <br>
      <el-input v-model="address" placeholder="请输入地址" class="myInput"></el-input>
      <br>
      <el-input v-model="phone" placeholder="请输入联系电话" class="myInput"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="addAddress">确定添加</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddAddress",
  data() {
    return {
      name: '',
      address: '',
      phone: ''
    }
  },
  methods: {
    addAddress() {
      if (this.name === '' || this.address === '' || this.phone === '') {
        this.$message.error('请填写完整信息');
      }
      else {
        axios //todo
          .post(
            'http://localhost:8080/addAddress',
            {
              'name': this.name,
              'address': this.address,
              'phone': this.phone
            }
          )
          .then(
            (response) => {
              alert('添加成功！');
              window.location = '/customer/addAddress';
            }
          )
      }
    }
  }
}
</script>

<style scoped>
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