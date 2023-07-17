package cn.edu.bupt.aiswitchboard.model;

import io.swagger.annotations.ApiModelProperty;


public class NameFinderFindRequest {
    @ApiModelProperty(value = "xxxxxx", example = "xxxxxx")
    @test1
    private String content;

    @ApiModelProperty(value = "xxxxxx", example = "xxxxxx")
    @test1
    private String corpId;

    @ApiModelProperty(value = "xxxxxx", example = "xxxxxx")
    @test2
    private String taskId;

    @ApiModelProperty(value = "xxxxxx", example = "xxxxxx")
    @test1
    @test2
    private String version;  // Noncompliant

    @ApiModelProperty(value = "xxxxxx", example = "xxxxxx")
    @test2
    @test1
    private String test;  // Noncompliant

    @test1
    @test2
    public NameFinderFindRequest() {
    }

    public NameFinderFindRequest(String content, String corpId, String taskId, String version) {
        this.content = content;
        this.corpId = corpId;
        this.taskId = taskId;
        this.version = version;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


public class Hello {
    @test1
    @test2
    private String test;
}