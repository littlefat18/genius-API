import {PageContainer} from '@ant-design/pro-components';
import React, {useEffect, useState} from 'react';
import {Button, Card, Descriptions, Form, message, Input, Spin, Divider, Image} from 'antd';
import {
  getInterfaceInfoByIdUsingGET,
  invokeInterfaceInfoUsingPOST,
} from '@/services/yuapi-backend/interfaceInfoController';
import {useParams} from '@@/exports';
import ReactJson from 'react-json-view';
import moment from "moment";
import VanillaJSONEditor from './VanillaJSONEditor';

/**
 * 主页
 * @constructor
 */
const Index: React.FC = () => {
  const [img, setImg] = useState(null);
  const [loading, setLoading] = useState(false);
  const [data, setData] = useState<API.InterfaceInfo>();
  const [invokeRes, setInvokeRes] = useState<any>();
  const param = new Object();
  const [invokeLoading, setInvokeLoading] = useState(false);
  const [content, setContent] = useState({
    json: param,
    text: undefined,
  });
  const params = useParams();

  const loadData = async () => {
    if (!params.id) {
      message.error('参数不存在');
      return;
    }
    setLoading(true);
    try {
      const res = await getInterfaceInfoByIdUsingGET({
        id: Number(params.id),
      });
      setData(res.data);
    } catch (error: any) {
      message.error('请求失败，' + error.message);
    }
    setLoading(false);
  };

  useEffect(() => {
    loadData();
  }, []);

  const onFinish = async (values: any) => {
    if (!params.id) {
      message.error('接口不存在');
      return;
    }
    setInvokeLoading(true);
    try {
      const res = await invokeInterfaceInfoUsingPOST({
        id: params.id,
        ...values,});
      // console.log(res);
      // const ref1 = res.data;
      // alert(ref1.value.code)
      if (res.data) {
        res.data = res.data.replace(/\\/g, '');
        // const jsonData = JSON.stringify(res.data as unknown as string);
        setInvokeRes(res.data);
        message.success('接口请求成功');
      } else {
        const messageObj = JSON.parse(res.message as string);
        message.error(messageObj.message);
      }
      if (params.id === '2') {
        setImg(res.data);
      }
      // setInvokeRes(res.data);
      // message.success('请求成功');
    } catch (error: any) {
      message.error('操作失败，' + error.message);
    }
    setInvokeLoading(false);
  };

  return (
    <PageContainer title="查看接口文档">
      <Card>
        {data ? (
          <Descriptions title={data.name} column={1}>
            <Descriptions.Item label="接口状态">{data.status ? '开启' : '关闭'}</Descriptions.Item>
            <Descriptions.Item label="描述">{data.description}</Descriptions.Item>
            <Descriptions.Item label="请求地址">{data.url}</Descriptions.Item>
            <Descriptions.Item label="请求方法">{data.method}</Descriptions.Item>
            <Descriptions.Item label="请求参数">{data.requestParams}</Descriptions.Item>
            <Descriptions.Item label="请求头">{data.requestHeader}</Descriptions.Item>
            <Descriptions.Item label="响应头">{data.responseHeader}</Descriptions.Item>
            <Descriptions.Item label="创建时间">{moment(data.createTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
            <Descriptions.Item label="更新时间">{moment(data.updateTime).format('YYYY-MM-DD HH:mm:ss')}</Descriptions.Item>
          </Descriptions>
        ) : (
          <>接口不存在</>
        )}
      </Card>
      {data ? (
        <>
          {' '}
          <Divider/>
          <Card title="在线测试">
            <Form name="invoke" layout="vertical" onFinish={onFinish}>
              {/*<Form.Item label="请求参数" name="userRequestParams">*/}
              {/*  /!*<div className="my-editor">*!/*/}
              {/*  /!*  <VanillaJSONEditor content={content} onChange={setContent} />*!/*/}
              {/*  /!*</div>*!/*/}
              {/*  <Input.TextArea/>*/}
              {/*</Form.Item>*/}
              <Form.Item
                label={'请求参数'}
                initialValue={data?.requestParams}
                name={'userRequestParams'}
              >
                <Input.TextArea defaultValue={data?.requestParams} rows={6}/>
              </Form.Item>
              <Form.Item wrapperCol={{span: 16}}>
                <Button type="primary" htmlType="submit">
                  调用
                </Button>
              </Form.Item>
            </Form>
          </Card>
          <Divider/>
          <Card title="返回结果" loading={invokeLoading}>
            {img ? <Image width={200} src={img}/> : invokeRes}
            {/*{img ? <Image width={200} src={img} /> : <ReactJson name={false} src={invokeRes} />}*/}
            {/*<Image src={invokeRes}/>*/}
            {/*{invokeRes}*/}
          </Card>
        </>
      ) : null}
    </PageContainer>
  );
};

export default Index;
