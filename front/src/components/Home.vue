<template>
  <div class="welcome-container">
    <!-- 背景动画 -->
    <div class="background-animation"></div>

    <!-- 主要内容 -->
    <div class="welcome-content">
      <h1 class="welcome-text">Welcome</h1>
    </div>
  </div>
</template>

<script>
export default {
  name: 'WelcomePage',
};
</script>

<style scoped>
.welcome-container {
  position: relative;
  height: 100%;
  padding: -10px;
  overflow: hidden;
  background-color: #ffffff; /* 背景色 */
  display: flex;
  justify-content: center;
  align-items: center;
}

.background-animation {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: radial-gradient(circle, rgba(255, 255, 255, 0.3) 10%, transparent 10.01%);
  background-size: 20px 20px;
  animation: moveBackground 3s linear infinite;
}

@keyframes moveBackground {
  0% {
    transform: translateY(0);
  }
  100% {
    transform: translateY(-20px);
  }
}

.welcome-content {
  position: relative;
  z-index: 1;
  text-align: center;
}

.welcome-text {
  font-size:15rem;
  font-weight: bold;
  color: #1e3c72; /* 文字颜色 */
  text-shadow: 0 0 20px rgba(255, 255, 255, 0.8);
  animation: glow 2s infinite alternate, fadeIn 2s ease-in-out;
}

@keyframes glow {
  0% {
    text-shadow: 0 0 10px rgba(255, 255, 255, 0.8);
  }
  100% {
    text-shadow: 0 0 30px rgba(255, 255, 255, 1);
  }
}

@keyframes fadeIn {
  0% {
    opacity: 0;
    transform: scale(0.8);
  }
  100% {
    opacity: 1;
    transform: scale(1);
  }
}
</style>