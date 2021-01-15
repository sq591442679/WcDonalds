<template>
  <div>
    <div class="customerBackground"></div>
    <el-menu
      router :default-openeds="['/customer']"
      v-for="item in $router.options.routes"
      :index="item.path"
      :key="item.path"
      v-if="item.path === '/customer'"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
    >
      <el-menu-item
        v-for="(item2, index) in item.children"
        :index="item2.path"
        :key="item2.path"
      >
        {{item2.name}}
      </el-menu-item>
    </el-menu>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: "Customer",
  data() {
    return {

    }
  },
  methods: {
  },
  mounted() {

  }
}
</script>

<style scoped>
.customerBackground {
  background: url("../../images/foods.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
  z-index: -1;
}

.mySelect {
  /*margin-top: 10px;*/
  width: 300px;
}
</style>