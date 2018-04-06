java -cp ConfigReader.jar cfg.cmd.CmdDefineHandler -f client -DefineOut java,ts,as3

java -cp ConfigReader.jar cfg.cmd.CmdDefineHandler -Field server -DefineOut java,ts,as3

java -cp ConfigReader.jar cfg.cmd.CmdDataHandler -F client -DataOut json,binary

java -cp ConfigReader.jar cfg.cmd.CmdDataHandler -Field server -DataOut json,binary

pause