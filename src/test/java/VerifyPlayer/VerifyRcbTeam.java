package VerifyPlayer;

import java.io.File;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class VerifyRcbTeam {
	
	File TeamSheet;
	
	@BeforeClass
    public void UploadTeamSheet() {
		TeamSheet = new File("src/test/resources/TeamRCB.json");
		}

	@Test
	public void verifyWicketKeeper() {
		boolean verifyKeeper = false;
		List<String> roles = JsonPath.from(TeamSheet).getList("player.role");
		for (int i=0; i< roles.size(); i++) {
			if (roles.get(i).equalsIgnoreCase("Wicket-keeper"))
				verifyKeeper = true;
		}
		Assert.assertTrue(verifyKeeper);
	}
	
	@Test
	public void verifyForeignPlayers() {
		int foreignPlayer = 0;
		List<String> country = JsonPath.from(TeamSheet).getList("player.country");
		for (int i=0; i<country.size(); i++) {
			if (!(country.get(i).equalsIgnoreCase("India")))
				foreignPlayer++;
			
		}
		Assert.assertTrue(foreignPlayer == 4);
	}

}
