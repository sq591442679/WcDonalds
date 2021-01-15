<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询职位信息</h1>
      <el-row style="max-height: 400px; overflow-y: auto">
        <ul>
          <li v-for="item in positionList" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "QueryPosition",
  data() {
    return {
      positionList: []
    }
  },
  mounted() {
    this.queryPosition();
  },
  methods: {
    queryPosition() {
      axios
        .post(
          'http://localhost:8080/queryPosition',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.positionList = response.data;
          }
        )
    }
  }
}
</script>

<style scoped>
.myInput {
  margin-top: 10px;
  width: 500px;
}

.sureButton {
  margin-top: 10px;
  position: relative;
  left: 50%;
  transform: translate(-50%);
}
</style>