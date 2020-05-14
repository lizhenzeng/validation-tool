package com.tigerobo.sample.controller;


import com.tigerobo.sample.entity.Sample;
import com.tigerobo.validation.ValditionUtils;
import com.tigerobo.validation.metadata.ConstructContainer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api(tags = "B端公司录入管理")
@RequestMapping("/sample")
public class SampleController  {

    @Autowired
    ValditionUtils valditionUtils;

    @ApiOperation(value = "测试put", tags = {"测试put"})
    @RequestMapping(path = "/queryHouseInfoList", method = RequestMethod.POST)
    public void queryHouseInfoList(@RequestBody Sample sample, HttpServletResponse response) throws IOException {
        ConstructContainer cc =  valditionUtils.getNotPassConstructContainer(sample);
        if(!cc.getIsPass()){
            response.getWriter().write(cc.getMessage());
        }
    }

}
