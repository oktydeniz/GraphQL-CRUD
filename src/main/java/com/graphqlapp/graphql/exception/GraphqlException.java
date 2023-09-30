package com.graphqlapp.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;

import java.net.UnknownServiceException;

@Component
public class GraphqlException extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof UnknownServiceException){
            return graphqlException(ex);
        } else if (ex instanceof Exception){
            return graphqlException(ex);
        }
        return super.resolveToSingleError(ex, env);
    }

    private GraphQLError graphqlException(Throwable t){
        return GraphqlErrorBuilder.newError().message(t.getMessage()).errorType(ErrorType.DataFetchingException).build();
    }

}
