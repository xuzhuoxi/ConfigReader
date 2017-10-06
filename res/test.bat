java -cp ConfigReader_1.0.jar cfg.cmd.CmdDefineHandler -Field client -DefineOut java,ts 

java -cp ConfigReader_1.0.jar cfg.cmd.CmdDefineHandler -Field server -DefineOut java,ts 

java -cp ConfigReader_1.0.jar cfg.cmd.CmdDataHandler -Field client -DefineOut json,binary 

java -cp ConfigReader_1.0.jar cfg.cmd.CmdDataHandler -Field server -DefineOut json,binary 

pause