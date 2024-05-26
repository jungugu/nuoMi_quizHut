import Taro from "@tarojs/taro";
import {useEffect, useState} from "react";
import {AtButton, AtRadio} from 'taro-ui';
import {View} from '@tarojs/components';
import "taro-ui/dist/style/components/button.scss";
import "taro-ui/dist/style/components/radio.scss";
import "taro-ui/dist/style/components/icon.scss";
import './index.scss';
import questions from '../../data/question.json';

/**
 *  做题页面
 */
export default () => {
  // 题号
  const [current, setCurrent] = useState<number>(1);
  // 当前题目
  const [currentQuestion, setCurrentQuestion] = useState(questions[0]);
  // 当前答案
  const [currentAnswer, setCurrentAnswer] = useState<String>();
  // 答案列表
  const [answerList] = useState<String[]>([]);
  const radioOptions = currentQuestion.options.map(option => {
    return {
      label: `${option.key}. ${option.value}`,
      value: option.key,
    };
  })
  // 改变题号时的数据加载
  useEffect(() => {
    // 初始化答案列表
    setCurrentQuestion(questions[current - 1]);
    // 初始化答案
    setCurrentAnswer(answerList[current - 1]);
  }, [current])

  return (
    <View className="doQuestionPage">
      <View className='at-article__h2 title'>{current + '.'}{currentQuestion.title}</View>
      <View className='option-wrapper'>
        <AtRadio
          options={radioOptions}
          value={currentAnswer}
          onClick={(value) => {
            setCurrentAnswer(value);
            answerList[current - 1] = value;
          }}
        />
      </View>
      {current < questions.length &&
        <AtButton type='primary' circle className='controlBtn' onClick={() => setCurrent(current + 1)}
          disabled={!currentAnswer}
        >下一题</AtButton>
      }

      {current == questions.length &&
        <AtButton type='primary' circle className='controlBtn' disabled={!currentAnswer} onClick={() => {
          Taro.setStorageSync('answerList', answerList);
          Taro.navigateTo({
            url: "/pages/result/index",
          });
        }}
        >查看答案</AtButton>
      }
      {current > 1 &&
        <AtButton type='secondary' circle className='controlBtn' onClick={() => setCurrent(current - 1)}>上一题</AtButton>
      }
    </View>
  );
};
