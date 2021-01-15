<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">用餐评价</h1>
      <el-select v-model="chosenOrder" placeholder="请选择您的订单" class="mySelect">
        <el-option
          v-for="item in orderList"
          :key="item"
          :label="item"
          :value="item">
        </el-option>
      </el-select>
      <br>
      <el-row style="margin-top: 20px; background-color: white; border-radius: 3px">
        <el-col :span="10" style="margin-left: 20px;">
          请为您的体验打分：
        </el-col>
        <el-col :span="8">
          <el-rate
            v-model="value"
            :colors="['#99A9BF', '#F7BA2A', '#FF9900']"
          >
          </el-rate>
        </el-col>
      </el-row>
      <br>
      <el-input
        type="textarea"
        :autosize="{ minRows: 2, maxRows: 4}"
        placeholder="请输入您的评价"
        v-model="feedbackText"
        class="myInput"
      >
      </el-input>
      <br>
      <el-button type="primary" @click="feedback" class="sureButton">确认评价</el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: "Feedback",
  data() {
    return {
      orderList: [], //历史订单列表
      chosenOrder: '',
      feedbackText: '',
      value: null,
    }
  },
  mounted() {
    this.init();
  },
  methods: {
    init() {
      axios
        .post(
          'http://localhost:8080/queryOrderTime',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.orderList = response.data;
          }
        )
    },
    feedback() {
      console.log(this.feedbackText);
      if (this.chosenOrder === '' || this.feedbackText === '') {
        this.$message.error('请填写完整信息');
      } else {
        axios
          .post(
            'http://localhost:8080/feedBack',
            {
              'orderTime': this.chosenOrder,
              'feedback': this.feedbackText,
              'feedbackTime': new Date().getTime().toString()
            }
          )
          .then(
            () => {
              alert('反馈成功！');
              window.location = '/customer/order';
            }
          )
      }
    }
  }
}
</script>

<style scoped>
.mySelect {
  margin-top: 10px;
  width: 500px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}

.myInput {
  margin-top: 10px;
  width: 500px;
}
</style>