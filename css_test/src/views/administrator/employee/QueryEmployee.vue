<template>
<div>
  <div class="front">
    <h1 class="centerAlign">查询员工信息</h1>
    <el-row style="max-height: 400px; overflow-y: auto">
      <ul>
        <li v-for="item in employeeList" style="margin-bottom: 10px">
          {{ item }}
        </li>
      </ul>
    </el-row>
  </div>
</div>
</template>

<script>
export default {
  name: "QueryEmployee",
  data() {
    return {
      employeeList: []
    }
  },
  mounted() {
    this.queryEmployee();
  },
  methods: {
    queryEmployee() {
      axios
        .post(
          'http://localhost:8080/queryEmployee',
          {
            'index': 1
          }
        )
        .then(
          (response) => {
            this.employeeList = response.data;
          }
        )
    }
  }
}
</script>

<style scoped>

</style>