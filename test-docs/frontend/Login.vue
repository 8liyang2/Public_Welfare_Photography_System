<template>
  <div class="login-page">
    <header class="header">
      <div class="logo" @click="goMain">公益摄影交流平台</div>
    </header>
    <main class="content">
      <div class="card">
        <h2>登录</h2>
        <form @submit.prevent="handleLogin">
          <label>
            账号（loginname）
            <input v-model="form.loginname" type="text" maxlength="20" required />
          </label>
          <label>
            密码（password）
            <input v-model="form.password" type="password" required />
          </label>
          <button type="submit">登录</button>
        </form>
        <p class="register-tip">
          还没有账号？
          <a href="javascript:void(0)" @click="goRegister">去注册</a>
        </p>
      </div>
    </main>
  </div>
</template>

<script setup>
import { reactive } from 'vue';
import axios from 'axios';

const form = reactive({
  loginname: '',
  password: ''
});

const handleLogin = async () => {
  try {
    const resp = await axios.post('/api/user/login', {
      loginname: form.loginname,
      password: form.password
    });
    if (resp.data && resp.data.success) {
      alert('登录成功，uid=' + resp.data.uid);
      // TODO: 根据 permission 跳转不同页面
    } else {
      alert('登录失败，请检查账号或密码');
    }
  } catch (e) {
    alert('请求失败：' + e);
  }
};

const goRegister = () => {
  window.location.href = '/user/login/register';
};

const goMain = () => {
  window.location.href = '/main';
};
</script>

<style scoped>
.login-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: linear-gradient(135deg, #1f2933, #3f4c6b);
  color: #fff;
}
.header {
  padding: 16px 32px;
}
.logo {
  font-size: 20px;
  font-weight: bold;
  cursor: pointer;
}
.content {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: center;
}
.card {
  background: rgba(255, 255, 255, 0.06);
  border-radius: 12px;
  padding: 32px 40px;
  box-shadow: 0 18px 45px rgba(0, 0, 0, 0.4);
  width: 360px;
}
label {
  display: block;
  font-size: 14px;
  margin-bottom: 16px;
}
input {
  width: 100%;
  margin-top: 6px;
  padding: 8px 10px;
  border-radius: 6px;
  border: none;
}
button {
  width: 100%;
  padding: 10px 0;
  border-radius: 6px;
  border: none;
  background: #3b82f6;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
}
.register-tip {
  margin-top: 12px;
  font-size: 14px;
  text-align: center;
}
.register-tip a {
  color: #93c5fd;
}
</style>

