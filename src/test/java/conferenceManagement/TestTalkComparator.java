package conferenceManagement;
import org.junit.Test;

import conferenceManagement.entities.Talk;
import conferenceManagement.utilities.TalkComparator;
import junit.framework.Assert;

public class TestTalkComparator {

	@Test
	public void testCompareDurationOneLessThanDurationTwo() {
		Talk t1 = new Talk("Java Talk", 45);
		Talk t2 = new Talk("Javascript Talk", 60);
		TalkComparator tc = new TalkComparator();
		
		Assert.assertEquals(1, tc.compare(t1, t2));
	}
	
	@Test
	public void testCompareDurationOneGreaterThanDurationTwo() {
		Talk t1 = new Talk("Java Talk", 60);
		Talk t2 = new Talk("Javascript Talk", 30);
		TalkComparator tc = new TalkComparator();
		
		Assert.assertEquals(-1, tc.compare(t1, t2));
	}
	
	@Test
	public void testCompareDurationOneEqualToDurationTwo() {
		Talk t1 = new Talk("Java Talk", 60);
		Talk t2 = new Talk("Javascript Talk", 60);
		TalkComparator tc = new TalkComparator();
		
		Assert.assertEquals(-1, tc.compare(t1, t2));
	}

}
