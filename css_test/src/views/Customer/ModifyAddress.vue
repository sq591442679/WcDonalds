<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">修改取餐信息</h1>
      <el-row>
        <el-select v-model="originAddress" placeholder="选择要修改的地址" style="margin-top: 10px; width: 400px">
          <el-option
            v-for="item in address"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-input v-model="newAddress" placeholder="请输入新的地址" class="myInput"></el-input>
      <br>
      <el-input v-model="name" placeholder="请输入新的联系人姓名" class="myInput"></el-input>
      <br>
      <el-input v-model="phone" placeholder="请输入新的联系电话" class="myInput"></el-input>
      <br>
      <el-button type="primary" class="sureButton" @click="modifyAddress">确认修改</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "ModifyAddress",
  data() {
    return {
      address: [],
      name: '',
      phone: '',
      originAddress: '',
      newAddress: ''
    }
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryAddress',
          {
            'index': 1,
          }
        )
        .then(
          (response) => {
            this.address = response.data;
          }
        )
    },
    modifyAddress() {
      if (this.originAddress === '' || this.phone === '' || this.name === '') {
        this.$message.error('请输入完整信息');
      }
      else {
        axios
          .post(
            'http://localhost:8080/modifyAddress',
            {
              'id': this.originAddress,
              'address': this.newAddress,
              'name': this.name,
              'phone': this.phone
            }
          )
        .then(
          (respone) => {
            alert('修改成功!');
            window.location = '/customer/modifyAddress';
          }
        )
      }
    }
  },
  mounted() {
    this.init();
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