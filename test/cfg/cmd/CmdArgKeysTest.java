package cfg.cmd;

import org.junit.Assert;
import org.junit.Test;

public class CmdArgKeysTest {

	@Test
	public void testHandleArgKey() {
		String key1 = "-source";
		System.out.println(CmdArgKeys.handleArgKey(key1));
		Assert.assertEquals(CmdArgKeys.KEY_SOURCE, CmdArgKeys.handleArgKey(key1));
	}
}
