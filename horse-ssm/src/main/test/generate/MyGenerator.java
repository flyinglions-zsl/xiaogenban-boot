package generate;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class MyGenerator {

    public static void main(String[] args) {
        String packageName = "com.example";
        generateByOracleTables(packageName,"TABLE_NAME");
    }

    /**
     * oracle
     *
     * @param packageName
     * @param tableNames
     */
    private static void generateByOracleTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "数据库地址";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.ORACLE)
                .setUrl(dbUrl)
                .setUsername("用户名")
                .setPassword("密码")
                .setDriverName("oracle.jdbc.driver.OracleDriver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
//                .setTablePrefix("表名前缀(生成的实体会省略这个前缀)")
                .setCapitalMode(true)//驼峰命名
                .setEntityLombokModel(true)//使用lombk
                //.setDbColumnUnderline(true)//驼峰命名
                .setRestControllerStyle(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("com.example.common.SuperEntity")
                .setSuperMapperClass("com.example.common.SuperMapper")
                .setSuperControllerClass("com.example.common.SuperController")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组

        config.setActiveRecord(false)
                .setAuthor("作者名")
                .setOutputDir("生成文件导出地址")
                .setEnableCache(false)
                .setBaseColumnList(true)
                .setBaseResultMap(true)
                .setFileOverride(true);

        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setService("service")
                                .setServiceImpl("serviceImp")
                                .setEntity("model")
                ).execute();

    }

    /**
     * mysql
     *
     * @param packageName
     * @param tableNames
     */
    private static void generateByTables(String packageName, String... tableNames) {
        GlobalConfig config = new GlobalConfig();
        String dbUrl = "数据库地址";
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL)
                .setUrl(dbUrl)
                .setUsername("root")
                .setPassword("123456")
                .setDriverName("com.mysql.jdbc.Driver");
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setCapitalMode(true)
                .setEntityLombokModel(false)
                //.setDbColumnUnderline(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass("com.example.common.SuperEntity")
                .setSuperMapperClass("com.example.common.SuperMapper")
                .setInclude(tableNames);//修改替换成你需要的表名，多个表名传数组
        config.setActiveRecord(false)
                .setAuthor("作者名")
                .setOutputDir("生成目录")
                .setFileOverride(true);
        new AutoGenerator().setGlobalConfig(config)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(
                        new PackageConfig()
                                .setParent(packageName)
                                .setController("controller")
                                .setService("service")
                                .setServiceImpl("serviceImp")
                                .setEntity("model")
                ).execute();
    }


}
