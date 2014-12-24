package me.risky.talk.server.persistence;

import me.risky.talk.server.domain.TContent;

import java.util.List;

/**
 * @author <a href="mailto:chenupt@gmail.com">jfchen</a>
 * @since 12/23/14
 */
public interface STContentMapper extends TContentMapper {

    public List<TContent> testQuery();

}
