package truelayer.java.auth;

import okhttp3.Interceptor;
import retrofit2.Retrofit;
import truelayer.java.ClientCredentials;
import truelayer.java.Environment;
import truelayer.java.versioninfo.VersionInfo;
import truelayer.java.http.HttpClientBuilder;
import truelayer.java.http.interceptors.IdempotencyKeyInterceptor;
import truelayer.java.http.interceptors.UserAgentInterceptor;
import truelayer.java.http.interceptors.logging.HttpLoggingInterceptor;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.apache.commons.lang3.Validate.notEmpty;
import static org.apache.commons.lang3.Validate.notNull;

public class AuthenticationHandlerBuilder {

    private Environment environment;

    private VersionInfo versionInfo;

    private ClientCredentials clientCredentials;

    AuthenticationHandlerBuilder() {}

    public AuthenticationHandlerBuilder environment(Environment environment) {
        this.environment = environment;
        return this;
    }

    public AuthenticationHandlerBuilder versionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
        return this;
    }

    public AuthenticationHandlerBuilder clientCredentials(ClientCredentials clientCredentials) {
        this.clientCredentials = clientCredentials;
        return this;
    }

    public AuthenticationHandler build() {
        notNull(clientCredentials, "signing options must be set");
        notEmpty(clientCredentials.clientId(), "key id must be not empty");
        notNull(clientCredentials.clientSecret(), "private key must be not empty.");

        List<Interceptor> networkInterceptors = Collections.singletonList(HttpLoggingInterceptor.New());

        List<Interceptor> applicationInterceptors = Arrays.asList(
                new IdempotencyKeyInterceptor(),
                new UserAgentInterceptor(versionInfo));

        Retrofit authHttpClient = new HttpClientBuilder()
                .baseUrl(environment.getAuthApiUri().toString())
                .applicationInterceptors(applicationInterceptors)
                .networkInterceptors(networkInterceptors)
                .build();

        return new AuthenticationHandler(versionInfo, environment, clientCredentials, authHttpClient.create(IAuthenticationApi.class));
    }
}
