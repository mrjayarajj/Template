package com.baseframework.log;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

/**
 * Compare against a log level that is associated with an MDC value.
 */
@Plugin(name = "DynamicThresholdUserFilter", category = "Core", elementType = "filter", printObject = true)
public final class DynamicThresholdUserFilter extends AbstractFilter {
	private Level defaultThreshold = Level.ERROR;
	private final String key;

	private DynamicThresholdUserFilter(final String key, final Level defaultLevel, final Result onMatch,
			final Result onMismatch) {
		super(onMatch, onMismatch);
		if (key == null) {
			throw new NullPointerException("key cannot be null");
		}
		this.key = key;
		this.defaultThreshold = defaultLevel;
	}

	public String getKey() {
		return this.key;
	}

	@Override
	public Result filter(final Logger logger, final Level level, final Marker marker, final String msg,
			final Object... params) {
		return filter(logger,level);
	}

	@Override
	public Result filter(final Logger logger, final Level level, final Marker marker, final Object msg,
			final Throwable t) {
		return filter(logger,level);
	}

	@Override
	public Result filter(final Logger logger, final Level level, final Marker marker, final Message msg,
			final Throwable t) {
		return filter(logger,level);
	}

	@Override
	public Result filter(final LogEvent event) {
		return filter(null,event.getLevel());
	}

	/* biggest change here */
	private Result filter(final Logger logger,final Level level) {
		final String enabled = ThreadContext.get(key);
		if(enabled!=null && enabled.equals("false")){
			return Result.NEUTRAL;
		}

		final String value = ThreadContext.get(logger.getName());

		if (value != null) {
			Level ctxLevel = Level.toLevel(value);
			if (ctxLevel == null) {
				// in case the level is invalid
				ctxLevel = defaultThreshold;
			}
			return level.isLessSpecificThan(ctxLevel) ? onMatch : onMismatch;
		}
		return Result.NEUTRAL;

	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("key=").append(key);
		sb.append(", default=").append(defaultThreshold);
		return sb.toString();
	}

	/**
	 * Create the DynamicThresholdFilter.
	 * 
	 * @param key
	 *            The name of the key to compare.
	 * @param pairs
	 *            An array of value and Level pairs.
	 * @param levelName
	 *            The default Level.
	 * @param match
	 *            The action to perform if a match occurs.
	 * @param mismatch
	 *            The action to perform if no match occurs.
	 * @return The DynamicThresholdFilter.
	 */
	@PluginFactory
	public static DynamicThresholdUserFilter createFilter(@PluginAttribute("key") final String key,
			@PluginAttribute("defaultThreshold") final String levelName, @PluginAttribute("onMatch") final String match,
			@PluginAttribute("onMismatch") final String mismatch) {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>LOG4j 2 Filter is Working<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		final Result onMatch = Result.toResult(match);
		final Result onMismatch = Result.toResult(mismatch);
		final Level level = Level.toLevel(levelName, Level.ERROR);
		return new DynamicThresholdUserFilter(key, level, onMatch, onMismatch);
	}
}
