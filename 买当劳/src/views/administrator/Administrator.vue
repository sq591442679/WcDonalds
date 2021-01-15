<template>
  <div>
    <div class="background"></div>
<!--    <h1>ADMIN</h1>-->
    <el-menu
      router :default-openeds="['/administrator']"
      v-for="item in $router.options.routes"
      :index="item.path"
      :key="item.path"
      v-if="item.path === '/administrator'"
      class="el-menu-demo"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-submenu index="1">
        <template slot="title">操作分组-餐厅</template>
          <el-menu-item
            v-for="(item2, index) in item.children"
            :index="item2.path"
            :key="item2.path"
            v-if="index < 4"
          >
            {{item2.name}}
          </el-menu-item>
      </el-submenu>
      <el-submenu index="2">
        <template slot="title">操作分组-员工</template>
        <el-menu-item
          v-for="(item2, index) in item.children"
          :index="item2.path"
          :key="item2.path"
          v-if="index >= 4 && index < 8"
        >
          {{item2.name}}
        </el-menu-item>
      </el-submenu>
      <el-submenu index="3">
        <template slot="title">操作分组-食品</template>
        <el-menu-item
          v-for="(item2, index) in item.children"
          :index="item2.path"
          :key="item2.path"
          v-if="index >= 8 && index < 12"
        >
          {{item2.name}}
        </el-menu-item>
      </el-submenu>
      <el-submenu index="4">
        <template slot="title">操作分组-套餐</template>
        <el-menu-item
          v-for="(item2, index) in item.children"
          :index="item2.path"
          :key="item2.path"
          v-if="index >= 12 && index < 16"
        >
          {{item2.name}}
        </el-menu-item>
      </el-submenu>
      <el-submenu index="5">
        <template slot="title">操作分组-入职记录</template>
        <el-menu-item
          v-for="(item2, index) in item.children"
          :index="item2.path"
          :key="item2.path"
          v-if="index >= 16 && index < 20"
        >
          {{item2.name}}
        </el-menu-item>
      </el-submenu>
      <el-submenu index="6">
        <template slot="title">操作分组-职位</template>
        <el-menu-item
          v-for="(item2, index) in item.children"
          :index="item2.path"
          :key="item2.path"
          v-if="index >= 20 && index < 24"
        >
          {{item2.name}}
        </el-menu-item>
      </el-submenu>
    </el-menu>
    <router-view></router-view>
<!--      <el-menu-->
<!--        router :default-openeds="['/administrator']"-->
<!--        v-for="item in $router.options.routes"-->
<!--        :index="item.path"-->
<!--        :key="item.path"-->
<!--        v-if="item.path === '/administrator'"-->
<!--        class="el-menu-demo"-->
<!--        mode="horizontal"-->
<!--        background-color="#545c64"-->
<!--        text-color="#fff"-->
<!--        active-text-color="#ffd04b"-->
<!--      >-->
<!--        <el-menu-item>-->
<!--        </el-menu-item>-->
<!--        <el-submenu-->
<!--          v-for="(group, index) in item.children"-->
<!--          :index="group.path"-->
<!--          :key="index"-->
<!--        >-->
<!--          <template slot="title">{{group.foodName}}</template>-->
<!--          <el-menu-item-->
<!--            v-for="(child, index2) in group.children"-->
<!--            :index="child.path"-->
<!--            :key="index2"-->
<!--            :class="$route.path === child.path ? 'is-active' : ''"-->
<!--          >-->
<!--            {{child.foodName}}-->
<!--          </el-menu-item>-->
<!--        </el-submenu>-->
<!--      </el-menu>-->
<!--    <router-view></router-view>-->
  </div>
</template>

<script>
export default {
  name: "Administrator",
  data() {
    return {
    }
  },
  methods: {
    test() {
      alert('NMSL');
    },
  }
}
</script>

<style scoped>
.background {
  background: url("../../images/seaside_background.png");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: 100% 100%;
  z-index: -1;
}
</style>