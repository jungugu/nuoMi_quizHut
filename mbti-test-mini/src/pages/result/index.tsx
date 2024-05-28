import { Image, View } from "@tarojs/components";
import {useEffect, useState} from "react";
import { AtButton } from "taro-ui";
import Taro from "@tarojs/taro";
import "./index.scss";
import GlobalFooter from "../../component/GlobalFooter/GlobalFooter";
import resultQuestion from "../../data/question_result.json";
import { getResult } from "../../utils/bizUtils";
import questions from "../../data/question.json";

/**
 * 主页
 */
export default () => {
  // 获取答案
  const answerList = Taro.getStorageSync("answerList");
  // 获取背景图
  const [resultBg, setResultBg] = useState<string>("../../assets/bg.svg");

  // 校验答案
  if (!answerList || answerList.length < 1) {
    Taro.showToast({
      title: "答案为空",
      icon: "error",
      duration: 3000,
    });
  }
  // 通过算法获取结果
  const result = getResult(answerList, questions, resultQuestion);
  console.log("result", result);

  // 获取结果后改变背景
  useEffect(() => {
    // setResultBg("../../assets/" + result.resultPicture);
    console.log("resultBg", resultBg);
  }, [result])

  return (
    <View className="resultPage">
      <View className="at-article__h1 title">{result?.resultName}</View>
      <View className="at-article__h3 subTitle">{result?.resultDesc}</View>
      <AtButton
        type="primary"
        size="normal"
        className="enterBtn"
        circle
        onClick={() => {
          Taro.reLaunch({
            url: "/pages/index/index",
          });
        }}
      >
        返回首页
      </AtButton>
      <Image
        className="headerBg"
        src={resultBg + ''}
        style={{ width: "100%" }}
        mode="aspectFill"
      />
      <GlobalFooter />
    </View>
  );
};
