import {Image, View} from '@tarojs/components'
import "taro-ui/dist/style/components/button.scss" // 按需引入
import './index.scss'
import { AtButton } from 'taro-ui'
import Taro from "@tarojs/taro";
import headerBg from "../../assets/header-mountains-mobile.svg";
import GlobalFooter from "../../component/GlobalFooter/GlobalFooter";

/**
 * 主页
 */
export default () => {
  return (
    <View className="indexPage">
      <View className='at-article__h1 title'>MBTI 性格测试</View>
      <View className='at-article__h3 subTitle'>
        只需10分钟，就能“惊人般准确”地描述出你是谁，以及你为何以这样的方式行事。
      </View>
      <AtButton
        type="primary"
        size="normal"
        className="enterBtn"
        circle
        onClick={() => {
          Taro.navigateTo({
            url: "/pages/doQuestion/index",
          });
        }}
      >
        参加测试
      </AtButton>
      <Image className='headerBg' src={headerBg} style={{width: '100%'}} mode="aspectFill" />
      <GlobalFooter />
    </View>
  );
};
