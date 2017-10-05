package ru.techlab.risks.rest.risksrestservices;

import org.joda.time.LocalDateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import ru.techlab.risks.rest.risksrestservices.model.BaseConfig;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityCategory;
import ru.techlab.risks.rest.risksrestservices.model.LoanQualityCategoryMatrix;
import ru.techlab.risks.rest.risksrestservices.model.LoanServCoeff;

import java.util.List;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class RisksRestServicesApplicationTests {
	private MockRestServiceServer mockServer;
	private RestTemplate restTemplate;

	@Before
	public void setup() {
		restTemplate = new RestTemplate();
		mockServer = MockRestServiceServer.createServer(restTemplate);
	}

	@Value("${app.configServer}")
	private String configServer;
	@Value("${app.path.config}")
	private String configPath;
	@Value("${app.path.loanqualitycategories}")
	private String loanQualityCategories;
	@Value("${app.path.loanservcoeffs}")
	private String loanServCoeffs;
	@Value("${app.path.loanservcoeffsmatrix}")
	private String loanServCoeffsMatrix;

	@Test
	public void getConfig() {
		String responceBody = "{\"id\":1,\"endOfDay\":\"2017-09-21\",\"jodaEndOfDay\":{\"era\":1,\"dayOfMonth\":21,\"dayOfWeek\":4,\"dayOfYear\":264,\"year\":2017,\"chronology\":{\"zone\":{\"fixed\":true,\"id\":\"UTC\"}},\"secondOfMinute\":0,\"millisOfSecond\":0,\"weekOfWeekyear\":38,\"centuryOfEra\":20,\"yearOfEra\":2017,\"yearOfCentury\":17,\"weekyear\":2017,\"monthOfYear\":9,\"hourOfDay\":0,\"minuteOfHour\":0,\"millisOfDay\":0,\"fields\":[{\"lenient\":false,\"rangeDurationField\":null,\"leapDurationField\":{\"precise\":true,\"unitMillis\":86400000,\"name\":\"days\",\"type\":{\"name\":\"days\"},\"supported\":true},\"minimumValue\":-292275054,\"maximumValue\":292278993,\"durationField\":{\"precise\":false,\"unitMillis\":31556952000,\"name\":\"years\",\"type\":{\"name\":\"years\"},\"supported\":true},\"name\":\"year\",\"type\":{\"durationType\":{\"name\":\"years\"},\"rangeDurationType\":null,\"name\":\"year\"},\"supported\":true},{\"lenient\":false,\"rangeDurationField\":{\"precise\":false,\"unitMillis\":31556952000,\"name\":\"years\",\"type\":{\"name\":\"years\"},\"supported\":true},\"leapDurationField\":{\"precise\":true,\"unitMillis\":86400000,\"name\":\"days\",\"type\":{\"name\":\"days\"},\"supported\":true},\"minimumValue\":1,\"maximumValue\":12,\"durationField\":{\"precise\":false,\"unitMillis\":2629746000,\"name\":\"months\",\"type\":{\"name\":\"months\"},\"supported\":true},\"name\":\"monthOfYear\",\"type\":{\"durationType\":{\"name\":\"months\"},\"rangeDurationType\":{\"name\":\"years\"},\"name\":\"monthOfYear\"},\"supported\":true},{\"rangeDurationField\":{\"precise\":false,\"unitMillis\":2629746000,\"name\":\"months\",\"type\":{\"name\":\"months\"},\"supported\":true},\"minimumValue\":1,\"maximumValue\":31,\"lenient\":false,\"durationField\":{\"precise\":true,\"unitMillis\":86400000,\"name\":\"days\",\"type\":{\"name\":\"days\"},\"supported\":true},\"unitMillis\":86400000,\"name\":\"dayOfMonth\",\"type\":{\"durationType\":{\"name\":\"days\"},\"rangeDurationType\":{\"name\":\"months\"},\"name\":\"dayOfMonth\"},\"supported\":true,\"leapDurationField\":null},{\"rangeDurationField\":{\"precise\":true,\"unitMillis\":86400000,\"name\":\"days\",\"type\":{\"name\":\"days\"},\"supported\":true},\"maximumValue\":86399999,\"range\":86400000,\"lenient\":false,\"durationField\":{\"name\":\"millis\",\"type\":{\"name\":\"millis\"},\"supported\":true,\"precise\":true,\"unitMillis\":1},\"minimumValue\":0,\"unitMillis\":1,\"name\":\"millisOfDay\",\"type\":{\"durationType\":{\"name\":\"millis\"},\"rangeDurationType\":{\"name\":\"days\"},\"name\":\"millisOfDay\"},\"supported\":true,\"leapDurationField\":null}],\"values\":[2017,9,21,0],\"fieldTypes\":[{\"durationType\":{\"name\":\"years\"},\"rangeDurationType\":null,\"name\":\"year\"},{\"durationType\":{\"name\":\"months\"},\"rangeDurationType\":{\"name\":\"years\"},\"name\":\"monthOfYear\"},{\"durationType\":{\"name\":\"days\"},\"rangeDurationType\":{\"name\":\"months\"},\"name\":\"dayOfMonth\"},{\"durationType\":{\"name\":\"millis\"},\"rangeDurationType\":{\"name\":\"days\"},\"name\":\"millisOfDay\"}]}}";

		mockServer.expect(requestTo(configServer + configPath))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responceBody, MediaType.APPLICATION_JSON));


		BaseConfig baseConfig = restTemplate
				.getForObject(configServer + configPath, BaseConfig.class);

		Assert.assertEquals(Integer.valueOf(1), baseConfig.getId());
		Assert.assertEquals(LocalDateTime.parse("2017-09-21"), baseConfig.getJodaEndOfDay());
	}

	@Test
	public void getloanQualityCategories() {
		String responceBody = "[{\"id\":5,\"type\":\"HOPELESS\",\"pmin\":100.0},{\"id\":1,\"type\":\"STANDARD\",\"pmin\":0.0},{\"id\":2,\"type\":\"NONSTANDARD\",\"pmin\":1.0},{\"id\":4,\"type\":\"PROBLEM\",\"pmin\":51.0},{\"id\":3,\"type\":\"DOUBTFUL\",\"pmin\":21.0}]";

		mockServer.expect(requestTo(configServer + loanQualityCategories))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responceBody, MediaType.APPLICATION_JSON));

		ResponseEntity<List<LoanQualityCategory>> response =
				restTemplate.exchange(configServer + loanQualityCategories,
						HttpMethod.GET, null, new ParameterizedTypeReference<List<LoanQualityCategory>>() {
						});
		List<LoanQualityCategory> loanQualityCategories = response.getBody();

		Assert.assertEquals(5, loanQualityCategories.size());
		Assert.assertEquals("NONSTANDARD", loanQualityCategories.get(2).getType());
	}

	@Test
	public void getLoanServCoeffs() {
		String responceBody = "[{\"type\":\"GOOD\",\"id\":3,\"lastDays\":180,\"moreThanDays\":180},{\"type\":\"MID\",\"id\":2,\"lastDays\":180,\"moreThanDays\":30},{\"type\":\"BAD\",\"id\":1,\"lastDays\":180,\"moreThanDays\":5}]";

		mockServer.expect(requestTo(configServer + loanServCoeffs))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responceBody, MediaType.APPLICATION_JSON));

		ResponseEntity<List<LoanServCoeff>> responce =
				restTemplate.exchange(configServer + loanServCoeffs,
						HttpMethod.GET, null, new ParameterizedTypeReference<List<LoanServCoeff>>() {
						});
		List<LoanServCoeff> loanQualityCategories = responce.getBody();
		Assert.assertEquals(3, loanQualityCategories.size());
	}

	@Test
	public void getLoanServCoeffsMatrix() {
		String responceBody = "[{\"loanServCoeffId\":1,\"finState1\":1,\"finState2\":2,\"finState3\":3},{\"loanServCoeffId\":2,\"finState1\":2,\"finState2\":3,\"finState3\":4},{\"loanServCoeffId\":3,\"finState1\":3,\"finState2\":4,\"finState3\":5}]";

		mockServer.expect(requestTo(configServer + loanServCoeffsMatrix))
				.andExpect(method(HttpMethod.GET))
				.andRespond(withSuccess(responceBody, MediaType.APPLICATION_JSON));

		ResponseEntity<List<LoanQualityCategoryMatrix>> responce =
				restTemplate.exchange(configServer + loanServCoeffsMatrix,
						HttpMethod.GET, null, new ParameterizedTypeReference<List<LoanQualityCategoryMatrix>>() {
						});
		List<LoanQualityCategoryMatrix> loanQualityCategoryMatrix = responce.getBody();
		Assert.assertEquals(3, loanQualityCategoryMatrix.size());
	}
}
