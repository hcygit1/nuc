<template>
    <view style="height: 100%;">
        <c-3d-model ref="modelRef" height="1500rpx" :src='src'
            :environmentSrc='environmentSrc' 
            :decoderPath='decoderPath' :autoRotate='autoRotate'  :isCenter='isCenter'
            :modelScale="modelScale" :ambientLight='0.5' @loaded='onLoaded'></c-3d-model>
    </view>
</template>
<!-- 3D模型 -->
<script>

    export default {
        data() {
            return {
				isCenter:true,
                height: '1000rpx',
                autoRotate:true,
                decoderPath:'https://cloud.vuedata.wang/draco/',
                modelScale:[0.025,0.025,0.025],
                // modelPosition:[-1.2,-2.0,1.5],
                // modelRotate:[0.1,0.1,0.2],
             //   src:'https://mp-eeab6da6-80cd-4e80-844a-66b2a7203834.cdn.bspapp.com/cloudstorage/8db3e88d-879f-4f1d-bf34-616f36ecad80.glb',
                src:'',
                environmentSrc:'https://mp-eeab6da6-80cd-4e80-844a-66b2a7203834.cdn.bspapp.com/cloudstorage/b45a40ff-e04f-43d7-afea-399e398ee35a.hdr'
            }
        },
        methods: {
            onLoaded() {
                console.log('模型加载完成');
            }
        },
        onUnload() {
            this.$refs.modelRef.call('dispose')
        },
		onLoad(options) {
			   console.log("== options.id=="+JSON.stringify(options))
		      this.src=options.src
		}
    }
</script>

<style lang="scss">
    .page{
        width: 100vw;
        overflow-x: hidden;
    }
    .content{
        padding: 20rpx;
        font-size: 28rpx;
    }
    .btnBox{
        width: 100%;
        display: flex;align-items: center;
        margin-top: 20rpx;
        margin-bottom: 30rpx;
    }
</style>