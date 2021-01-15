<template>
  <div>
    <div class="front">
      <h1 class="centerAlign">查询套餐</h1>
      <el-row style="max-height: 400px; overflow-y: auto">
        <ul>
          <li v-for="item in sets" style="margin-bottom: 10px">
            {{ item }}
          </li>
        </ul>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminQuerySet",
  data() {
    return {
      sets: [],
    }
  },
  mounted() {
    this.querySet();
  },
  methods: {
    querySet() {
      axios
        .post(
          'http://localhost:8080/querySet',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            if (response.data === 0) {
              this.$message.error('订单为空！');
            }
            else {
              this.sets = response.data;
            }
          }
        )
    }
  }

}
</script>

<style scoped>

</style>