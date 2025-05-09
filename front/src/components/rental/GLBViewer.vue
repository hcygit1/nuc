<template>
  <div ref="viewerContainer" class="glb-viewer-container"></div>
</template>

<script>
import * as THREE from 'three';
import { GLTFLoader } from 'three/examples/jsm/loaders/GLTFLoader.js';
import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';

export default {
  name: 'GLBViewer',
  props: {
    glbUrl: {
      type: String,
      required: true,
    },
  },
  data() {
    return {
      scene: null,
      camera: null,
      renderer: null,
      controls: null,
      mixer: null, // 用于动画播放
      clock: new THREE.Clock(), // 用于动画更新
      model: null, // 存储加载的 3D 模型
    };
  },
  mounted() {
    this.initThreeJS();
    this.loadGLBModel(this.glbUrl);
    this.animate();
  },
  methods: {
    initThreeJS() {
      // 创建场景
      this.scene = new THREE.Scene();
      this.scene.background = new THREE.Color(0x808080); // 灰色背景

      // 创建相机
      const width = this.$refs.viewerContainer.clientWidth;
      const height = this.$refs.viewerContainer.clientHeight;
      this.camera = new THREE.PerspectiveCamera(75, width / height, 0.1, 1000);
      this.camera.position.set(5, 5, 5); // 初始相机位置

      // 创建渲染器
      this.renderer = new THREE.WebGLRenderer({antialias: true});
      this.renderer.setSize(width, height);
      this.$refs.viewerContainer.appendChild(this.renderer.domElement);

      // 添加光源
      const ambientLight = new THREE.AmbientLight(0xffffff, 0.5);
      this.scene.add(ambientLight);
      const directionalLight = new THREE.DirectionalLight(0xffffff, 1);
      directionalLight.position.set(5, 5, 5).normalize();
      this.scene.add(directionalLight);

      // 添加轨道控制器
      this.controls = new OrbitControls(this.camera, this.renderer.domElement);
      this.controls.enableDamping = true; // 启用阻尼效果
      this.controls.dampingFactor = 0.25;
      this.controls.screenSpacePanning = false;
      this.controls.minDistance = 1;
      this.controls.maxDistance = 50;
    },
    loadGLBModel(url) {
      const loader = new GLTFLoader();
      loader.load(
          url,
          (gltf) => {
            this.model = gltf.scene;
            this.scene.add(this.model);

            // 自动居中定位
            const box = new THREE.Box3().setFromObject(this.model);
            const center = new THREE.Vector3();
            box.getCenter(center);
            this.model.position.sub(center); // 将模型中心移动到原点

            // 设置控制器目标为模型中心
            this.controls.target.copy(center);

            // 如果有动画，自动播放
            if (gltf.animations && gltf.animations.length > 0) {
              this.mixer = new THREE.AnimationMixer(this.model);
              gltf.animations.forEach((clip) => {
                this.mixer.clipAction(clip).play();
              });
            }
          },
          undefined,
          (error) => {
            console.error('加载 GLB 模型失败:', error);
          }
      );
    },
    animate() {
      requestAnimationFrame(this.animate);

      // 更新动画
      if (this.mixer) {
        const delta = this.clock.getDelta();
        this.mixer.update(delta);
      }

      // 更新控制器
      this.controls.update();

      // 渲染场景
      this.renderer.render(this.scene, this.camera);
    },
  },
  beforeDestroy() {
    // 清理资源
    if (this.renderer) {
      this.renderer.dispose();
    }
  },
};
</script>

<style scoped>
.glb-viewer-container {
  width: 100%;
  height: 500px;
  background-color: #f0f0f0;
}
</style>