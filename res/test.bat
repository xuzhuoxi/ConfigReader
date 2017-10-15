java -cp ConfigReader.jar cfg.cmd.CmdDefineHandler -Field client -DefineOut java,ts

java -cp ConfigReader.jar cfg.cmd.CmdDefineHandler -Field server -DefineOut java,ts

java -cp ConfigReader.jar cfg.cmd.CmdDataHandler -Field client -DataOut json,binary

java -cp ConfigReader.jar cfg.cmd.CmdDataHandler -Field server -DataOut json,binary

pause