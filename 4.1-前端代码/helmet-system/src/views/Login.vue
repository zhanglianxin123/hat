<template>
  <div class="container">
    <div class="leftContainer"><img src="../assets/images/login/图层.png" alt=""></div>
    <div class="loginContainer">
      <div class="logoContainer">
        <img src="../assets/images/logo/logo.png" alt="">
      </div>
      <div class="textContainer">
        <h2>智能安全帽</h2>
      </div>
      <el-form :model="loginData" ref="loginData" label-width="0" :rules="rules">
        <el-form-item prop="id">
          <div class="inputContainer">
            <el-input
              placeholder="请输入账号"
              v-model="loginData.id"
              prefix-icon="el-icon-user">
            </el-input>
          </div>
        </el-form-item>
        <el-form-item prop="password">
          <div class="inputContainer">
            <el-input
              placeholder="请输入密码"
              v-model="loginData.password"
              show-password
              prefix-icon="el-icon-lock">
            </el-input>
          </div>
        </el-form-item>
        <el-form-item>
          <div class="btnContainer">
            <el-button type="primary" @click="userLogin">登录</el-button>
          </div>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import http from '@/utils/http'

export default {
  name: 'Login',
  data () {
    return {
      loginData: {
        id: '',
        password: ''
      },
      rules: {
        id: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    }
  },
  methods: {
    userLogin () {
      this.$refs.loginData.validate((valid) => {
        if (valid) {
          http.post('/Login/login', this.loginData).then(resp => {
            if (resp.code === 200) {
              this.$message.success(resp.message)
              //  存储用户token
              window.localStorage.token = resp.data.token
              this.$router.push('/home')
            }
          })
        } else {
          this.$message.error('登陆失败')
          return false
        }
      })
    }
  }
}
</script>

<style scoped lang="less">
.container {
  width: 100%;
  height: 100%;
  position: absolute;
  background-image: url("../assets/images/globalImages/loginBG.png");
  background-repeat: no-repeat;
  background-size: 100% 100%;
  display: flex;
  .leftContainer {
    img {
      position: relative;
      height: 500px;
      top: 100px;
      left: 100px;
    }
  }
}
.loginContainer {
  width: 400px;
  height: 500px;
  position: relative;
  left: 400px;
  top: 100px;
  border: 1px #EBEEF5 solid;
  background-color: #FFF;
  overflow:hidden;
  color:#303133;
  border-radius: 4px;
  box-shadow:0 2px 12px 0 rgba(0,0,0,.1);
  -webkit-box-shadow:0 2px 12px 0 rgba(0,0,0,.1);
  .logoContainer {
    width: 120px;
    height: 120px;
    margin: 30px auto;
    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
    }
  }
  .textContainer {
    width: 240px;
    height: 32px;
    margin: 10px auto;
    h2 {
      text-align: center;
    }
  }
  .btnContainer {
    width: 240px;
    height: 40px;
    margin: 25px auto;
    .el-button {
      width: 240px;
      height: 40px;
      border-radius: 4px;
    }
  }
  .el-form {
    .el-form-item {
      margin-bottom: 0;
      .inputContainer {
        width: 240px;
        margin: 20px auto;
      }
    }
  }
}
/deep/.el-form-item__error {
  margin-left: 80px;
}
/deep/.el-form-item__content {
  height: 60px;
}
</style>
