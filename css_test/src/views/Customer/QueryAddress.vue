<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询取餐信息</h1>
      <el-row v-if="flag">
        <ul>
          <li v-for="item in address" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "QueryAddress",
  data() {
    return {
      address: [],
      flag: false
    }
  },
  mounted() {
    this.queryAddress();
  },
  methods: {
    queryAddress() {
      this.flag = true;
      axios
        .post(
          'http://localhost:8080/queryAddress',
          {
            'index': '1'
          }
        )
        .then(
          (response) => {
            if (response.data === 0) {
              this.$message.error('取餐信息为空！');
            }
            else {
              this.address = response.data;
              console.log(this.address)
            }
          }
        )
    }
  }
}
</script>

<style scoped>
.sureButton {
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>