# ConfigReader
这是一个游戏工具，用于读出Excel配置表，生成对应数据文件和定义。

## 兼容性
- Ant 1.9.6
- JDK 1.8

## 主要功能
- Excel配置表 => 数据文件
- Excel配置表 => 数据结构定义(包含数据读取方法)
- Json定义 => 数据结构定义

## 如何使用

### 目录及配置文件说明
+ source/	：默认读取目录
	* 在不指定具体配置目录或文件时，读取配置文件的默认目录，支持子文件夹。
	* 可通过修改[system.json](/res/system.json)文件中`Source`配置项进行重新指定。
	* 支持的文件类型为`*.xls`，`*.exls`，`*.json`。
	* 使用命令行参数-Source(-s或-S)时忽略当前配置。

+ target/	：默认输出目录
	* 在不指定具体输出目录时，输出文件的默认根目录。
	* 可通过修改[system.json](/res/system.json)文件中``Target``配置项进行重新指定。
	* 使用命令行参数-Target(-t或-T)时忽略当前配置。

+ lib/	：本项目使用到的工具库，**不建议修改**。

+ proxy/	：读取数据文件是可能用到的代理类，内中文件可复制到具体项目中使用。

+ ConfigReader.jar	：本项目打包成的jar，本工具的的全部功能入口。

+ langs/	：编程语言相关的配置
	+ DataFormat:配置表字段类型对语言数据类型的映射。
	+ Get:数据读取时调用的方法名，现支持Json、二进制读取，对应子键分别为json、binary。
	+ Set:数据写入时调用的方法名，现支持Json、二进制读取，对应子键分别为json、binary。
	+ Temp:对应的模板配置。
		1. "${PACKAGE_CONTENT}": 包定义模板，可嵌套"${CLASS_CONTENT}
		2. "${CLASS_CONTENT}": 类定义模板，可嵌套"${PROPERTY_CONTENT}",
		3. "${PROPERTY_CONTENT}": 属性模板，可嵌套"${PARSE_BINARY_CONTENT}"，"${PARSE_JSON_CONTENT}"。
		4. "${PARSE_BINARY_CONTENT}": 解释一个二进制属性的模板文件,
		5. "${PARSE_JSON_CONTENT}": 解释一个Json属性的模板文件。
	+ 完整例子：[java.json](/res/langs/java.json)

+ template/	：模板文件目录。
	+ 建议分目录存放，如java的模板就放到 **langs/java/** 中。
	+ 建议模板文件命名：*.java.temp，其中java为对应的编程语言。

+ [project.json](/res/project.json)	：项目自定义配置
	+ ClientOut：客户端输出信息，B列为数据结构名，C列为单独数据文件名(不包含扩展名)
	+ ServerOut：服务端输出信息，B列为数据结构名，C列为单独数据文件名(不包含扩展名)
	+ DBOut：数据库输出信息，B列为表名，C列为单独数据文件名(不包含扩展名)
	+ Keys：主键信息，暂时没用到
	+ Name：数据名称
	+ Remark：数据注释
	+ Valid：输出选择，格式: 'c,s,d'，c、s、d的格式只能是0或1，c指前端，s指后端，d指数据库，顺序不能颠倒
	+ DataType：数据格式,单元格格式目前支持{uint8,uint16,uint32,int8,int16,int32,boolean,string,string(\*)},其中string中的*代表字节数
	+ FieldName：导出定义时对应的字段名、属性名或key。(db)为数据库字段名，(json)为key，(java,ts,c++,c#)为属性名
	+ StartRow：数据开始行号(Excel左则行号)
	**注意**：以前配置的数据值从1开始，例如数据注释行为表单的第六行，那Remark.value应该配置为6。

+ [system.json](/res/system.json)	：基础配置
	+ Source：默认读取目录。
	+ Target：默认输出目录。
	+ Buff：处理数据时缓存池长度配置，单位为字节，token指一个单元格数据，item指一行数据，sheet指一个表数据。
	+ FileType：支持输出文件格式，现在暂时支持json,binary(二进制),sql。
	+ Langs：输出数据定义时支持的格式(编程语言)。
	+ DataFormat：支持输出的数据格式。**注意**：string包含了`string`和`string(*)`两种数据格式。
	+ SheetPrefix：Excel配置表中sheet名称前缀，包含当前前缀的sheet表单才会被处理。

### 功能扩展与定制
#### 新增编程语言支持(假设新语言为：abc)
1. 修改[system.json](/res/system.json)文件，在Langs.value值补充新语言定义标识（自定义即可，当前为`"abc"`）。
2. 修改OutputDefineLangType.java文件，增加对应枚举`Abc("abc")`，以及补充getExtensionName方法：`case Abc: return "abc";`。
3. 修改FieldKey.java文件，增加对应枚举`Abc("abc")`。
4. 增加对应的Excel配置文件中的语言字段行；修改[project.json](/res/project.json)文件，在FieldName.value增加新语言字段对应的配置文件行号映射。
	**注意**：如果配置文件修改后带来其它行号变化，要把[project.json](/res/project.json)文件的相应行号修改。
5. 增加模板文件到**template/abc/**，增加语言配置文件**langs/abc.json**，
	配置说明请参考langs/说明。
6. 执行build.bat文件重新打包(非Windows用户自行使用Ant命令)。

#### 新增数据文件格式扩展
1. 修改OutputDataFormat.java文件，增加新枚举。
2. 执行build.bat文件重新打包(非Windows用户自行使用Ant命令)。

#### 新增数据字段格式扩展
1. 修改FieldDataFormat.java文件，增加新的FieldDataFormat常量定义；补充FieldDataFormat.from方法。
2. ContentSerializeHandlerMap.java文件，在构造方法中增加handlerMap映射。
3. 修改[system.json](/res/system.json)文件，补充DataFormat.value值。
4. 修改lang/下使用到的语言配置，分别补充DataFormat、Get、Set下的对应数据结构或方法对应的名称。
5. 执行build.bat文件重新打包(非Windows用户自行使用Ant命令)。

### 命令行运行
命令行功能基于Java命令行格式

#### 生成数据文件
命令行格式： `java -cp ConfigReader.jar cfg.cmd.CmdDataHandler -Field client -DataOut json,binary`<br>
+ `java -cp ConfigReader.jar cfg.cmd.CmdDataHandler`是基本的Java命令行。
+ 参数`-Field`用于选择Excel表单的数据有效性(必要)。
+ 有效值为`client`、`server`、`db`，在FieldRangeType.java文件中有枚举定义。
	+ [project.json](/res/project.json)文件中Valid字段用于指定Excel表单数据有效性定义所在行数。
	+ 数据有效性定义格式：`c,s,d`，其中c,s,d只能是0或1。c为1时，参数值选择client则当前列数据将参数处理，否则不处理。
	+ 段对应值为1的数据进行处理。
+ 参数`-DataOut`指定输出的数据文件格式(必要)。
	+ 有效值为`json`、`binary`、`sql`，在OutputDataFormat.java文件中有枚举定义。
	+ 支持多种数据文件同时输出，中间以英文逗号(`,`)相隔。
+ 可选参数`-Source`，`-Target`，可用于指定Excel配置表来源位置和指定输出文件位置。

#### 生成定义文件
命令行格式： `java -cp ConfigReader.jar cfg.cmd.CmdDefineHandler -Field server -DefineOut java,ts`<br>
+ 参数`-Field`用于选择Excel表单的数据有效性(必要)。
	+ 有效值为`client`、`server`、`db`，在FieldRangeType.java文件中有枚举定义。
	+ [project.json](/res/project.json)文件中Valid字段用于指定Excel表单数据有效性定义所在行数。
	+ 数据有效性定义格式：```c,s,d```，其中c,s,d只能是0或1。s为1时，参数值选择server则当前列数据将参数处理，否则不处理。
	+ 段对应值为1的数据进行处理。
+ 参数`-DefineOut`指定输出的编程语言数据结构定义(必要)。
	+ 有效值由为`java`, `ts`, `c++`, `c#`，在system.json文件中Langs.value中定义。
	+ 支持多种数据文件同时输出，中间以英文逗号(`,`)相隔。
+ 可选参数`-Source`，`-Target`，可用于指定Excel配置表来源位置和指定输出文件位置。
