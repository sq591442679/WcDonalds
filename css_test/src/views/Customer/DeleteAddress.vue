<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">删除取餐信息</h1>
      <el-row>
        <el-select v-model="chosenAddress" placeholder="选择要删除的地址" style=" margin-top: 10px; width: 400px">
          <el-option
            v-for="item in addressList"
            :value="item"
          >
          </el-option>
        </el-select>
      </el-row>
      <el-button type="primary" class="sureButton" @click="deleteAddress">确认删除</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "DeleteAddress",
  data() {
    return {
      addressList: [],
      chosenAddress: '',
    }
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryAddress',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.addressList = response.data;
          }
        )
    },
    deleteAddress() {
      if (this.chosenAddress === '') {
        this.$message.error('请完整填写信息');
      }
      else {
        //this.$message.success("DFGHJK");
        console.log(this.chosenAddress);
        //todo
        axios
          .post(
            'http://localhost:8080/deleteAddress',
            {
              'id': this.chosenAddress
            }
          )
        .then(
          (respone) => {
            alert('修改成功!');
            window.location = '/customer/deleteAddress';
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
.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>