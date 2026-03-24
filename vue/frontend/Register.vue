<template>
  <div class="register-page">
    <header class="header">
      <div class="logo" @click="goMain">公益摄影交流平台</div>
    </header>
    <main class="content">
      <div class="card">
        <h2>注册</h2>
        <form @submit.prevent="handleRegister">
          <label>
            账号（loginname）
            <input v-model="form.loginname" type="text" maxlength="20" required />
          </label>
          <label>
            密码（password）
            <input v-model="form.password" type="password" required />
          </label>
          <label>
            再次输入密码（password1）
            <input v-model="form.password1" type="password" required />
          </label>
          <label>
            头像图片（avatar，可选，自动转 Base64）
            <input type="file" accept="image/*" @change="onPickAvatar" />
          </label>
          <button type="submit">注册</button>
        </form>
        <p class="login-tip">
          已有账号？
          <a href="javascript:void(0)" @click="goLogin">去登录</a>
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
  password: '',
  password1: '',
  avatar: '' // Base64 DataURL
});

const onPickAvatar = async (e) => {
  const file = e.target.files && e.target.files[0];
  if (!file) return;
  // 600KB 左右的图片转 Base64 后会变大，这是正常现象
  const reader = new FileReader();
  reader.onload = () => {
    form.avatar = reader.result; // data:image/...;base64,...
  };
  reader.readAsDataURL(file);
};

const handleRegister = async () => {
  try {
    const resp = await axios.post('/api/user/register', {
      loginname: form.loginname,
      password: form.password,
      password1: form.password1,
      avatar: form.avatar || null
    });
    const data = resp.data;
    if (data && data.success) {
      alert('注册成功，您的用户ID为：' + data.user_id);
      window.location.href = '/user/login';
    } else {
      if (data.loginname_exists === 1) {
        alert('注册失败：用户名已存在');
      } else if (data.password_exists === 0) {
        alert('注册失败：两次密码输入不一致');
      } else {
        alert('注册失败');
      }
    }
  } catch (e) {
    alert('请求失败：' + e);
  }
};

const goLogin = () => {
  window.location.href = '/user/login';
};

const goMain = () => {
  window.location.href = '/main';
};
</script>

<style scoped>
.register-page {
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background: linear-gradient(135deg, #1f2933, #29323c);
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
  width: 380px;
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
  background: #10b981;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
}
.login-tip {
  margin-top: 12px;
  font-size: 14px;
  text-align: center;
}
.login-tip a {
  color: #6ee7b7;
}
</style>

